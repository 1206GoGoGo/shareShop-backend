package whut.service.impl;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
		if( userLogin.getStatus()!=1 && userLogin.getStatus()!=2 && userLogin.getStatus()!=3 ) {
			return new ResponseData(4064,"status exception",null);
		}
		
		String ip = getIp(SysContent.getRequest());
		
		//验证成功创建安全验证信息sercity
		String sercity = EncryptUtil.MD5(username+new Date());	//每次请求更新，写到过滤器或拦截器中
		
		UserLoginLog userLoginLog = new UserLoginLog(ip, 1, userLogin.getUserId());
		loginLogDao.addLoginLog(userLoginLog);

		//将登录状态保存到redis中，session只保存用户id，并且有效期可以短点，减轻服务器负担。redis中登录状态可以保存2天等
		Jedis jedis = JedisUtil.getJedis();
		jedis.set("login:"+username+":userid", userLogin.getUserId().toString());	//增加或覆盖用户名
		jedis.set("login:"+username+":tz", sercity);	//用户身份验证信息
		jedis.expire("login:"+username+":tz", 60*60*24*2); //保存2天
    	JedisUtil.closeJedis(jedis);
    	
		//设置session
        HttpSession session = SysContent.getSession();
		session.setAttribute("userName",userLogin.getUsername());
		session.setAttribute("userId",userLogin.getUserId());
		session.setMaxInactiveInterval(60*60*2);//保存2小时
		return new ResponseData(200,"login success",userLogin.getUsername()+"q=my_"+sercity);
	}

	private String getIp(HttpServletRequest request) {
		//request为HttpServletRequest对象
	    String ip = request.getHeader("X-Real-IP");
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("X-Forwarded-For");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    // 处理多IP的情况（只取第一个IP）
	    if (ip != null && ip.contains(",")) {
	        String[] ipArray = ip.split(",");
	        ip = ipArray[0];
	    }
		return ip;
	}

	@Override
	public ResponseData loginout() {
        HttpSession session = SysContent.getRequest().getSession();
        
        String userName = null;
        try {
        	userName = SysContent.getUserName();
        }catch(Exception e){
        	try{
        		userName = SysContent.getRequest().getHeader("Authorization").split("q=my_", 2)[0];
        	}catch(Exception e2){
        		return new ResponseData(400,"no login",null);
        	}
        }
        
        
		//清除redis中的验证信息
		Jedis jedis = JedisUtil.getJedis();
		jedis.del("login:"+userName+":tz");
    	JedisUtil.closeJedis(jedis);
        
    	
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
