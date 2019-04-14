package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.UserCollect;

public interface UserCollectDao {

	List<UserCollect> getListByUser(int id);

	Object getAmountById(int id);

	
	//通过productId、userId获取唯一的一条收藏信息
	UserCollect getCollect(Map<String, Integer> map);

	//新增收藏
	void add(UserCollect userCollect);

	//删除收藏
	void delete(int collectId);

	//通过collectId获取收藏
	UserCollect getCollectByCollectId(int collectId);


}