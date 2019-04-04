package whut.dao;

import java.util.List;

import whut.pojo.UserAddr;

public interface UserAddrDao {

	List<UserAddr> getListByUserId(int id);
}
