package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.UserInfo;

public interface UserInfoDao {

	//String status,String pageindex, String pagesize
	//获取完整用户信息--不包括seller
	List<UserInfo> getList(Map<String, Integer> map);
	
	List<UserInfo> getSellerList(Map<String, Object> map);

	void add(UserInfo user);

	//修改用户状态
	void delete(int id);

	//修改用户信息表
	void modify(UserInfo user);

	//通过用户对象获取用户全部信息（两个表的信息）
	//无数据返回list为空
	//String pagesize, String pageindex, String username, String phoneNumber,String name,String identityCardNo, String level
	//int status, String email
	List<UserInfo> searchAllInfoByUserInfo(Map<String, Object> map);

	//map包括int pagesize, int pageindex,int superiorId通过上线id获取下线列表，只返回正常状态用户
	List<UserInfo> getMemberBySellerId(Map<String, Integer> map);
	
	//通过登录表id获取用户信息
	UserInfo getUserInfo(String id);

	
}
