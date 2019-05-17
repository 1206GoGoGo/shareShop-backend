package whut.service.impl;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import whut.dao.UserLoginDao;
import whut.dao.UserLoginLogDao;
import whut.pojo.UserLogin;
import whut.pojo.UserLoginLog;
import whut.service.MemberLoginService;
import whut.utils.EncryptUtil;
import whut.utils.JedisUtil;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;
import whut.utils.SysContent;
@Service
public class MemberLoginServiceImpl implements MemberLoginService {
	
	@Autowired
	private UserLoginDao loginDao;
	
	@Autowired
	private UserLoginLogDao loginLogDao;
	
	@Override
	public ResponseData loginin(String jsonString) {
		
		JsonUtils jsonUtils = new JsonUtils(jsonString);
		String username = jsonUtils.getStringValue("username");
		String password = jsonUtils.getStringValue("password");

		UserLogin userLogin = loginDao.getLoginInfo(username);
		
		if(userLogin == null) {
			return new ResponseData(406,"parameters incorrect",null);
		}
		
		if( !EncryptUtil.MD5(password).equals(userLogin.getPassword())) {
			return new ResponseData(4061,"password error",null);
		}
		//seller登录后台界面需要验证该信息
//		if( userLogin.getLevel()!=20 ) {
//			return new ResponseData(4063,"inadequate permissions",null);
//		}
		if( userLogin.getStatus()!=1 ) {
			return new ResponseData(4064,"status exception",null);
		}
		
		//验证成功创建安全验证信息sercity
		String sercity = EncryptUtil.MD5(username+new Date());	//每次请求更新，写到过滤器或拦截器中
		
		UserLoginLog userLoginLog = new UserLoginLog("111.111.111.111", 1, userLogin.getUserId());
		loginLogDao.addLoginLog(userLoginLog);
		//---------------------------------------------------------------------------------------设置客户端验证信息token或cookie-----两种方式
		logininSetCookie(userLogin,sercity);
		//logininSetToken(userLogin,sercity);

		//将登录状态保存到redis中，session只保存用户id，并且有效期可以短点，减轻服务器负担。redis中登录状态可以保存2天等
		Jedis jedis = JedisUtil.getJedis();
		jedis.set("login:"+username+":userid", userLogin.getUserId().toString());	//增加或覆盖用户名
		jedis.set("login:"+username+":_tzBDSFRCVID", sercity);	//用户身份验证信息
		jedis.expire("login:"+username+":_tzBDSFRCVID", 60*60*24*2); //保存2天
    	JedisUtil.closeJedis(jedis);
    	
		//设置session
        HttpSession session = SysContent.getSession();
		session.setAttribute("userName",userLogin.getUsername());
		session.setAttribute("userId",userLogin.getUserId());
		session.setMaxInactiveInterval(60*60*2);//保存2小时
		return new ResponseData(200,"login success",userLogin.getUsername()+"q=my_"+sercity);
	}
	
	private void logininSetToken(UserLogin userLogin, String sercity) {
		HttpServletResponse response = SysContent.getResponse();
		response.addHeader("Authorization", userLogin.getUsername()+"q=my_"+sercity);
	}
	
	private void logininSetCookie(UserLogin userLogin, String sercity) {
		
		HttpServletResponse response = SysContent.getResponse();
		//设置cookie
		Cookie dot = new Cookie("_dotcom_user", userLogin.getLevel().toString());
		dot.setPath("/");
		dot.setMaxAge(60*60*24*30);
		response.addCookie(dot);
		//用户名（每次请求前端带到后台）
		Cookie userna = new Cookie("_octouser", userLogin.getUsername());
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
	}

	@Override
	public ResponseData loginout() {
        HttpSession session = SysContent.getRequest().getSession();
        
		//清除redis中的验证信息
		Jedis jedis = JedisUtil.getJedis();
		jedis.del("login:"+SysContent.getUserName()+":_tzBDSFRCVID");
    	JedisUtil.closeJedis(jedis);
        
    	//----移除cookie信息
    	loginoutRemoveCookie();
        //清除session
		session.invalidate();
    	
		return new ResponseData(200,"success",null);
	}
	
	private void loginoutRemoveCookie() {
		//最近活跃0/1（8个小时内，活跃1，否则不存在）
		Cookie activity = new Cookie("has_recent_activity", "0");
		activity.setPath("/");
		activity.setMaxAge(60*60*8);
		SysContent.getResponse().addCookie(activity);
		//登录状态0/1（24小时为1，否则不存在）
		Cookie logged = new Cookie("logged_in", "0");
		logged.setPath("/");
		logged.setMaxAge(60*60*24);
		SysContent.getResponse().addCookie(logged);
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
