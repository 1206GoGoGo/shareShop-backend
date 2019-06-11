package whut.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import redis.clients.jedis.Jedis;
import whut.utils.JedisUtil;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * 默认知道当前会话，如果前端不能传递sessionId需要进行识别。（这块还没有处理），否则每次请求都会建立一个新的session--或者完全不用session<很快过期>
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String requestUri = ((HttpServletRequest)request).getRequestURI();
		//用户登录不过滤
		
		if( requestUri.indexOf("/member/login/in")>-1 ) {
			//继续执行
			chain.doFilter(request,response);
			return;
		}

		String userId = null;
        String userName = null;
        String sercityOldCookieOrToken = null;
		String sercityOldRedis = null;

		sercityOldCookieOrToken = getTokenVerify((HttpServletRequest) request);
		
		if(sercityOldCookieOrToken == null) {
			if(testIsNeedLogin(requestUri)) {
				//无需登录即可访问
				chain.doFilter(request,response);
				return;
			}
			response.setContentType("application/json;charset=UTF-8");
        	response.getWriter().print( "{\"code\":403,\"msg\":\"用户未登录1\",\"data\": null}");
        	return;
		}
        
		Jedis jedis = JedisUtil.getJedis();	
        
		//判断session存储的用户信息是否失效,获取用户id及用户名
		try {
			userId = ((HttpServletRequest) request).getSession().getAttribute("userId").toString();
			userName =  ((HttpServletRequest) request).getSession().getAttribute("userName").toString();
		}catch(Exception e) {
			//session登录信息已清除
			userName = getTokenUserName((HttpServletRequest) request);
			

			if(userName == null) {
				if(testIsNeedLogin(requestUri)) {
					//无需登录即可访问
					chain.doFilter(request,response);
					return;
				}
				response.setContentType("application/json;charset=UTF-8");
	        	response.getWriter().print( "{\"code\":403,\"msg\":\"用户未登录2\",\"data\": null}");
	        	return;
			}
			try{
				userId = jedis.get("login:"+userName+":userid");
			}catch(Exception e2) {
				JedisUtil.closeJedis(jedis);
				if(testIsNeedLogin(requestUri)) {
					//无需登录即可访问
					chain.doFilter(request,response);
					return;
				}
				response.setContentType("application/json;charset=UTF-8");
	        	response.getWriter().print( "{\"code\":403,\"msg\":\"用户未登录3\",\"data\": null}");
	        	return;
			}
		}
		

		//获取redis中的验证信息
		try {
			sercityOldRedis = jedis.get("login:"+userName+":tz");
		}catch(Exception e) {
			JedisUtil.closeJedis(jedis);
			if(testIsNeedLogin(requestUri)) {
				//无需登录即可访问
				chain.doFilter(request,response);
				return;
			}
			response.setContentType("application/json;charset=UTF-8");
        	response.getWriter().print( "{\"code\":403,\"msg\":\"用户未登录4\",\"data\": null}");
        	return;
		}

		
		//判断客户端发送的安全验证是否符合条件
        if(!sercityOldCookieOrToken.equals(sercityOldRedis)) {
			JedisUtil.closeJedis(jedis);
			if(testIsNeedLogin(requestUri)) {
				//无需登录即可访问
				chain.doFilter(request,response);
				return;
			}
			response.setContentType("application/json;charset=UTF-8");
        	response.getWriter().print( "{\"code\":403,\"msg\":\"用户未登录5\",\"data\": null}");
        	return;
        }
		//验证成功，生成安全验证信息，并转发
		//获取session中的验证信息（暂时不用session存储登录信息）

		//对于登录模式仅登录需要返回token值
		//setToken(userName, sercity,(HttpServletResponse) response);
		HttpSession session = ((HttpServletRequest) request).getSession();
		session.setAttribute("userId",userId);
		session.setAttribute("userName",userName);
		session.setMaxInactiveInterval(60*30);//session保存30分钟
    	
        //jedis刷新过期时间
		//jedis.set("login:"+userName+":userid", userId);	//增加或覆盖用户id，不设置过期
		//jedis.set("login:"+userName+":_tzBDSFRCVID", sercity);	//用户身份验证信息
		jedis.expire("login:"+userName+":tz", 60*60*24*2);
    	JedisUtil.closeJedis(jedis);

		chain.doFilter(request,response);
	}

	//true表示可以不登录
	private boolean testIsNeedLogin(String requestUri) {
		boolean needLogin1 = requestUri.indexOf("/member/login/in")>-1;	
		boolean needLogin2 = requestUri.indexOf("/pro/info")>-1;
		boolean needLogin3 = requestUri.indexOf("/member/login/add")>-1;
		
		
		boolean needLogin = needLogin1 || needLogin2 || needLogin3;
		return needLogin;
	}

	private void setCookie(String sercity, HttpServletResponse response) {
		Cookie logininfo = new Cookie("_tzBDSFRCVID", sercity);
		logininfo.setPath("/");
		logininfo.setMaxAge(60*60*24);
		response.addCookie(logininfo);
		//最近活跃0/1（8个小时内，活跃1，否则不存在）
		Cookie activity = new Cookie("has_recent_activity", "1");
		activity.setPath("/");
		activity.setMaxAge(60*60*8);
		response.addCookie(activity);
	}

	private String getTokenUserName(HttpServletRequest request) {
		String userName= null;
		try{
			userName = request.getHeader("Authorization").split("q=my_", 2)[0];
		}catch(Exception e) { }
		return userName;
	}



	private String getTokenVerify(HttpServletRequest request) {
		String sercityOldToken= null;
		try{
			sercityOldToken = request.getHeader("Authorization").split("q=my_", 2)[1];
		}catch(Exception e) { }
		return sercityOldToken;
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
