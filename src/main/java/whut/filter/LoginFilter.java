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

import whut.utils.EncryptUtil;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//用户登录不过滤
		if( ((HttpServletRequest)request).getRequestURI().indexOf("/manager/login")>-1 ) {
			//继续执行
			chain.doFilter(request,response);
			return;
		}
		

		//获取cookie和session
        String sercityOldCookie = null;
        String username = null;
        String sercityOldSession = null;
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        HttpSession session = ((HttpServletRequest) request).getSession();
		try {
			
	        //获取cookie
			if(cookies!=null) {
				for(Cookie cookie:cookies) {
					if(cookie.getName().equals("_tzBDSFRCVID")) {
						sercityOldCookie = cookie.getValue();
						break;
					}
				}
			}
	        
	        //获取session
	        username = session.getAttribute("_octouser").toString();//用户名
	        sercityOldSession = session.getAttribute("_tzBDSFRCVID").toString();
		}catch(Exception e) {
			response.setContentType("application/json;charset=UTF-8");
        	response.getWriter().print( "{\"code\":403,\"msg\":\"用户未登录\",\"data\": null}");
        	return;
		}
		
		//判断客户端发送的安全验证是否符合条件
        if(!sercityOldCookie.equals(sercityOldSession)) {
			response.setContentType("application/json;charset=UTF-8");
        	response.getWriter().print( "{\"code\":403,\"msg\":\"用户未登录\",\"data\": null}");
        	return;
        }
        
		//验证成功
        //生成安全验证信息
        //并转发
        String sercity = EncryptUtil.MD5(username+new Date());
		session.setAttribute("_tzBDSFRCVID",sercity);
		session.setMaxInactiveInterval(60*60*24);//有效期1天
		session.setAttribute("_octouser",username);
		session.setMaxInactiveInterval(60*60*24);
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
