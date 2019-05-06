package whut.filter;

import java.io.IOException;
import java.util.Date;

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
import whut.utils.EncryptUtil;
import whut.utils.JedisUtil;
import whut.utils.SysContent;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//用户登录不过滤
		if( ((HttpServletRequest)request).getRequestURI().indexOf("/member/login")>-1 ) {
			//继续执行
			chain.doFilter(request,response);
			return;
		}
		
		String userId = null;
        String userName = null;
        String sercityOldCookie = null;
		String sercityOldRedis = null;
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        
		//获取cookie中的验证信息，不存在直接验证失败
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("_tzBDSFRCVID")) {
					sercityOldCookie = cookie.getValue();
					break;
				}
			}
		}
		if(sercityOldCookie == null) {
			response.setContentType("application/json;charset=UTF-8");
        	response.getWriter().print( "{\"code\":403,\"msg\":\"用户未登录\",\"data\": null}");
        	return;
		}
        
		Jedis jedis = JedisUtil.getJedis();	
        
		//判断session存储的用户信息是否失效,获取用户id及用户名
		try {
			userId = String.valueOf(SysContent.getUserId());
			userName = SysContent.getUserName();
		}catch(Exception e) {
			//session登录信息已清除
			//获取cookie中的用户名
			if(cookies!=null) {
				for(Cookie cookie:cookies) {
					if(cookie.getName().equals("_octouser")) {
						userName = cookie.getValue();
						break;
					}
				}
			}
			if(userName == null) {
				response.setContentType("application/json;charset=UTF-8");
	        	response.getWriter().print( "{\"code\":403,\"msg\":\"用户未登录\",\"data\": null}");
	        	return;
			}
			try{
				userId = jedis.get("login:"+userName+":userid");
			}catch(Exception e2) {
				JedisUtil.closeJedis(jedis);
				response.setContentType("application/json;charset=UTF-8");
	        	response.getWriter().print( "{\"code\":403,\"msg\":\"用户未登录\",\"data\": null}");
	        	return;
			}
		}
		

		//获取redis中的验证信息
		try {
			sercityOldRedis = jedis.get("login:"+userName+":_tzBDSFRCVID");
		}catch(Exception e) {
			JedisUtil.closeJedis(jedis);
			response.setContentType("application/json;charset=UTF-8");
        	response.getWriter().print( "{\"code\":403,\"msg\":\"用户未登录\",\"data\": null}");
        	return;
		}

		
		//判断客户端发送的安全验证是否符合条件
        if(!sercityOldCookie.equals(sercityOldRedis)) {
			JedisUtil.closeJedis(jedis);
			response.setContentType("application/json;charset=UTF-8");
        	response.getWriter().print( "{\"code\":403,\"msg\":\"用户未登录\",\"data\": null}");
        	return;
        }
		//验证成功，生成安全验证信息，并转发
		//获取session中的验证信息（暂时不用session存储登录信息）
        HttpSession session = ((HttpServletRequest) request).getSession();
        String sercity = EncryptUtil.MD5(userName+new Date());
		session.setAttribute("userId",userId);
		session.setAttribute("userName",userName);
		session.setMaxInactiveInterval(60*60*1);//session保存1小时
		
		jedis.set("login:"+userName+":userid", userId);	//增加或覆盖用户id，不设置过期
		jedis.set("login:"+userName+":_tzBDSFRCVID", sercity);	//用户身份验证信息
		jedis.expire("login:"+userName+":_tzBDSFRCVID", 60*60*24*2);
    	JedisUtil.closeJedis(jedis);
		
		//同步更新cookie
		Cookie logininfo = new Cookie("_tzBDSFRCVID", sercity);
		logininfo.setPath("/");
		logininfo.setMaxAge(60*60*24);
		( (HttpServletResponse)response ).addCookie(logininfo);
		//最近活跃0/1（8个小时内，活跃1，否则不存在）
		Cookie activity = new Cookie("has_recent_activity", "1");
		activity.setPath("/");
		activity.setMaxAge(60*60*8);
		( (HttpServletResponse)response ).addCookie(activity);

		chain.doFilter(request,response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
