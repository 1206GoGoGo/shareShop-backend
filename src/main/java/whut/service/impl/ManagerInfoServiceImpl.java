package whut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ManagerInfoDao;
import whut.dao.UserLoginDao;
import whut.pojo.ManagerInfo;
import whut.pojo.UserLogin;
import whut.service.ManagerInfoService;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;
@Service
public class ManagerInfoServiceImpl implements ManagerInfoService {

	@Autowired
	private ManagerInfoDao dao;
	
	@Autowired
	private UserLoginDao loginDao;

	@Override
	public ResponseData getList(int status) {
		List<ManagerInfo> list = dao.getList(status);
		if(list.isEmpty()) {
			return new ResponseData(400,"no data",null);
		}else {
			return new ResponseData(200,"success",list);
		}
	}

	@Override
	public ResponseData add(ManagerInfo managerInfo) {
		String username = managerInfo.getUserLogin().getUsername();
		String password = managerInfo.getUserLogin().getPassword();
		//判断用户名、密码是否符合规则
		if(username.length()<5) {
			return new ResponseData(4061,"unqualified username",null);}
		if(this.checkPassWordMethod(password)) {
			return new ResponseData(4062,"unqualified password",null);
		}
		
		//判断用户名是否冲突
		if(loginDao.getLoginInfo(username)!=null) {
			return new ResponseData(4063,"username is occupied",null);
		}
		
		//添加用户登录表数据
		UserLogin userLogin = new UserLogin();
		userLogin.setUsername(username);
		userLogin.setPassword(password);
		userLogin.setLevel( managerInfo.getUserLogin().getLevel() );	//设置用户等级、管理员类型需要传入
		userLogin.setStatus((byte)1);	//设置用户状态
		loginDao.addUser(userLogin);
		
		
		//刷新，将执行数据插入操作，之后获取添加数据对象

		userLogin = loginDao.getLoginInfo(username);
		
		System.out.println(userLogin.getUserId());
		//给user对象赋值id
		managerInfo.setUserId(userLogin.getUserId());
		managerInfo.setUserLogin(userLogin);

		//判断用户名是否重复
		dao.add(managerInfo);
		return new ResponseData(200,"success",null);

	}

	@Override
	public ResponseData getDetail(int id) {
		ManagerInfo info = dao.getDetail(id);
		if(info != null) {
			return new ResponseData(200,"success",info);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData modify(ManagerInfo managerInfo) {
		ManagerInfo managerInfoOld = dao.getDetail(managerInfo.getUserId());
		if(managerInfoOld == null) {
			//需要修改的管理员编号不存在
			return new ResponseData(4061,"Administrator does not exist",null);
		}
		
		//判断当前管理员状态
		if( managerInfoOld.getUserLogin().getStatus() == 0 ) {
			return new ResponseData(4062,"Administrator status exception",null);
		}
		
		//只处理部分参数的修改
		managerInfoOld.setName(managerInfo.getName());
		managerInfoOld.setIdentityCardType(managerInfo.getIdentityCardType());
		managerInfoOld.setIdentityCardNo(managerInfo.getIdentityCardNo());
		managerInfoOld.setPhoneNumber(managerInfo.getPhoneNumber());
		managerInfoOld.setEmail(managerInfo.getEmail());
		managerInfoOld.setGender(managerInfo.getGender());
		managerInfoOld.setBirthday(managerInfo.getBirthday());
		
		dao.modify(managerInfoOld);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData delete(String jsonString) {
		int id = new JsonUtils(jsonString).getIntValue("id");
		dao.delete(id);
		return new ResponseData(200,"success",null);
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

}
