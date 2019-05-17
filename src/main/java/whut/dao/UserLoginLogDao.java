package whut.dao;

import whut.pojo.UserLoginLog;

public interface UserLoginLogDao {

	//新增用户登录信息
	void addLoginLog(UserLoginLog userLoginLog);

}
