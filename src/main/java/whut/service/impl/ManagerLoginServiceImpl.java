package whut.service.impl;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.UserLoginDao;
import whut.pojo.UserLogin;
import whut.service.ManagerLoginService;
import whut.utils.EncryptUtil;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;
@Service
public class ManagerLoginServiceImpl implements ManagerLoginService {
	
	@Autowired
	private UserLoginDao loginDao;
	
	@Override
	public ResponseData loginin(String jsonString, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
		
		JsonUtils jsonUtils = new JsonUtils(jsonString);
		String username = jsonUtils.getStringValue("username");
		String password = jsonUtils.getStringValue("password");

		UserLogin userLogin = loginDao.getLoginInfo(username);
		if( !EncryptUtil.MD5(password).equals(userLogin.getPassword())) {
			return new ResponseData(4061,"password error",null);
		}
		if( userLogin.getLevel()!=20 ) {
			return new ResponseData(4063,"inadequate permissions",null);
		}
		if( userLogin.getStatus()!=1 ) {
			return new ResponseData(4064,"Administrator status exception",null);
		}
		
		//验证成功创建安全信息sercity及加密
		String sercity = EncryptUtil.MD5(username+new Date());	//每次请求更新，写到过滤器或拦截器中
		
		//设置cookie
		
		//用户等级。数字
		Cookie dot = new Cookie("_dotcom_user", userLogin.getLevel().toString());
		dot.setPath("/");
		dot.setMaxAge(60*60*24*30);
		response.addCookie(dot);
		//用户名（每次请求前端带到后台）
		Cookie userna = new Cookie("_octouser", username);
		userna.setPath("/");
		userna.setMaxAge(60*60*24*30);
		response.addCookie(userna);
		//最近活跃0/1（8个小时内，活跃1，否则不存在）
		Cookie activity = new Cookie("has_recent_activity", "1");
		activity.setPath("/");
		activity.setMaxAge(60*60*8);
		response.addCookie(activity);
		//登录状态0/1（24小时为1，否则不存在）
		Cookie logged = new Cookie("logged_in", "1");
		logged.setPath("/");
		logged.setMaxAge(60*60*24*365);
		response.addCookie(logged);
		//用户登录信息，安全验证（每次请求前端带到后台）
		Cookie logininfo = new Cookie("_tzBDSFRCVID", sercity);
		logininfo.setPath("/");
		logininfo.setMaxAge(60*60*24);
		response.addCookie(logininfo);
		
		//设置session
		//用户登录信息
		session.setAttribute("_tzBDSFRCVID",sercity);
		session.setMaxInactiveInterval(60*60*24);//有效期1天
		//用户名
		session.setAttribute("_octouser",username);
		session.setMaxInactiveInterval(60*60*24);

		return new ResponseData(200,"login success",null);
	}

	@Override
	public ResponseData loginout(String username, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        
		//最近活跃0/1（8个小时内，活跃1，否则不存在）
		Cookie activity = new Cookie("has_recent_activity", "0");
		activity.setPath("/");
		activity.setMaxAge(60*60*8);
		response.addCookie(activity);
		//登录状态0/1（24小时为1，否则不存在）
		Cookie logged = new Cookie("logged_in", "0");
		logged.setPath("/");
		logged.setMaxAge(60*60*24);
		response.addCookie(logged);
        //清除session
		session.invalidate();
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData getPhoneCode(String phoneCode) {
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData getMailCode(String mailCode) {
		return new ResponseData(200,"success",null);
	}

}
