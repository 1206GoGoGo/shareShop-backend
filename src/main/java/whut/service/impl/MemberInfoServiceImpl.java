package whut.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.OrderDao;
import whut.dao.UserInfoDao;
import whut.dao.UserLoginDao;
import whut.pojo.UserInfo;
import whut.pojo.UserLogin;
import whut.service.MemberInfoService;
import whut.utils.EncryptUtil;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;
import whut.utils.SysContent;
@Service
public class MemberInfoServiceImpl implements MemberInfoService {

	@Autowired
	private UserInfoDao dao;

	@Autowired
	private UserLoginDao loginDao;
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public ResponseData memberAdd(UserInfo user){

		String username = user.getUserLogin().getUsername();
		String password = user.getUserLogin().getPassword();
		//判断用户名、密码是否符合规则
		if(username.length()<5) {
			return new ResponseData(4065,"unqualified username",null);}
		if(this.checkPassWordMethod(password)) {
			return new ResponseData(4066,"unqualified password",null);
		}
		
		//查询，处分页都可能为空
		Map<String,Object> map = new HashMap<>();
		map.put("pageindex", 0);
		map.put("pagesize", 1);
		map.put("username", null);
		map.put("phoneNumber", null);
		map.put("name", null);
		map.put("identityCardNo", null);
		map.put("level", null);
		map.put("email", null);
		
		//判断信息是否冲突
		
		if(loginDao.getLoginInfo(username)!=null) {
			return new ResponseData(4061,"username is occupied",null);
		}
		
		String phoneNumber = user.getPhoneNumber();
		map.put("phoneNumber", phoneNumber);
		if(!dao.searchAllInfoByUserInfo(map).isEmpty()) {
			return new ResponseData(4062,"phoneNumber is occupied",null);
		}
		map.put("phoneNumber", null);
		
		String email = user.getEmail();
		map.put("email", email);
		if(!dao.searchAllInfoByUserInfo(map).isEmpty()) {
			return new ResponseData(4063,"email is occupied",null);
		}
		map.put("email", null);
		
		String identityCardNo = user.getIdentityCardNo();
		map.put("identityCardNo", identityCardNo);
		if(!dao.searchAllInfoByUserInfo(map).isEmpty()) {
			//return new ResponseData(4064,"identityCardNo is occupied",null);
		}
		map.put("identityCardNo", null);
		
		//添加用户登录表数据
		UserLogin userLogin = new UserLogin();
		userLogin.setUsername(username);
		userLogin.setPassword(EncryptUtil.MD5(password));
		userLogin.setLevel(1);	//设置用户等级
		userLogin.setStatus((byte)1);	//设置用户状态
		
		loginDao.addUser(userLogin);
		
		userLogin = loginDao.getLoginInfo(username);
		
		//给user对象赋值
		user.setName(null);
		user.setUserId(userLogin.getUserId());
		user.setUserLogin(userLogin);

		//添加用户
		try {
			dao.add(user);
		}catch(Exception e) {
			loginDao.deleteUserLoginForError(userLogin.getUserId());
			return new ResponseData(5001,"system registration exception",null);
		}
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData delete() {
		UserLogin userLogin = loginDao.getLoginInfoById(SysContent.getUserId());
		if(userLogin == null) {
			return new ResponseData(406,"user does not exist",null);
		}
		
		//判断用户状态（已是删除状态禁止删除）
		if(userLogin.getStatus()==0) {
			return new ResponseData(4061,"user status exception",null);
		}
		//店主禁止删除（该操作入口禁止删除）
		if(userLogin.getLevel()==3) {
			return new ResponseData(4062,"prevent deletion of Shopkeeper identity",null);
		}
		
		//判断该用户是否产生过订单
		Map<String, Integer> map = new HashMap<>();
		map.put("pageindex", 1);
		map.put("pagesize", 1);
		//map.put("id", id);
		//if(orderDao.getListByUser(map)!=null) {
		//	return new ResponseData(4063,"the user has order information",null);
		//}
		
		//dao.delete(id);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData modify(UserInfo user) {
		UserInfo userOld = dao.getUserInfo( String.valueOf(SysContent.getUserId()) );
		//修改用户信息，密码、登录名、证件号、账户余额禁止修改(编号识别要修改的用户)。需要判断是否满足指定条件，如果用户状态已经是注销状态禁止修改。
		
		//判断当前用户状态
		if( userOld.getUserLogin().getStatus() == 0 ) {
			return new ResponseData(4061,"user status exception",null);
		}
		
		//只处理部分参数的修改
		userOld.setName(user.getName());
		userOld.setIdentityCardType(user.getIdentityCardType());
		userOld.setIdentityCardNo(user.getIdentityCardNo());
		userOld.setPhoneNumber(user.getPhoneNumber());
		userOld.setEmail(user.getEmail());
		userOld.setGender(user.getGender());
		userOld.setBirthday(user.getBirthday());
		
		dao.modify(userOld);
		return new ResponseData(200,"success",null);

	}

	@Override
	public ResponseData getDetail() {
		UserInfo info = dao.getUserInfo(String.valueOf(SysContent.getUserId()));
		if(info != null) {
			return new ResponseData(200,"success",info);
		}else {
			return new ResponseData(400,"no data satify request",null);
		}
	}

	@Override
	public ResponseData getMemberListBySeller(int pagesize, int pageindex, String username) {
		int superiorId;
		try {
			superiorId = loginDao.getLoginInfo(username).getUserId();
		}catch(Exception e) {
			return new ResponseData(4061,"user does not exist",null);
		}

		List<UserInfo> list = null;
		Map<String,Integer> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		map.put("superiorId", superiorId);
		list = dao.getMemberBySellerId(map);
		if(list==null || list.isEmpty() ) {
			return new ResponseData(4062,"promoter has not downline",null);
		}
		
		return new ResponseData(200,"success",list);
	}
	
	//密码校验
    private boolean checkPassWordMethod(String str) {
        char[] ch1 = str.toCharArray();
        boolean flag = false;
        int num_count = 0, char_number = 0;
        for (int i = 0; i < ch1.length; i++) {
            if (Character.isDigit(ch1[i]) || Character.isLetter(ch1[i])) {
                if (Character.isDigit(ch1[i]))
                    num_count++;
                else
                    char_number++;
            } else
                break;
        }
        if (num_count >= 2 && char_number >= 8)
            flag = true;
        System.out.println("num_count=" + num_count + ",char_number=" + char_number);
        return flag;
    }

	@Override
	public ResponseData getCountAWeek(int userId) {
		Map<String,Object> map = new HashMap<>();
		map.put("sellerId", userId);
		String list = "[";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		Date d=cal.getTime();
		for(int i=0;i<7;i++) {
			String day = df.format(d);
			list += "{\"data\":\""+day+"\",\"user\":";
			map.put("day", day);
			list += loginDao.getCountADay( map ) + ",\"member\":";

			map.put("day", day);
			list += loginDao.getCountADay( map );

			if(i<6) {
				list += "},";
			}

	        cal.add(Calendar.DATE,-1);
	        d=cal.getTime();
		}
		list += "}]";
        
		//System.out.println(list);
		
		return  new ResponseData(200,"success",list);
	}

}
