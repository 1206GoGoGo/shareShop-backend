package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.UserInfo;

public interface UserInfoDao {
	
	List<UserInfo> getSellerList(Map<String, Object> map);

	void add(UserInfo user);

	//修改用户状态
	void delete(int id);

	//修改用户信息表
	void modify(UserInfo user);

	//map包括int pagesize, int pageindex,int superiorId通过上线id获取下线列表，只返回正常状态用户
	List<UserInfo> getMemberBySellerId(Map<String, Integer> map);
	
	//通过登录表id获取用户信息
	UserInfo getUserInfo(String id);

	
}
