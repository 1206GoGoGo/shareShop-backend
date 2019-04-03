package whut.dao;

import java.util.List;

import whut.pojo.UserCollect;

public interface UserCollectDao {

	List<UserCollect> getListByUser(int id);

	Object getAmountById(int id);

}