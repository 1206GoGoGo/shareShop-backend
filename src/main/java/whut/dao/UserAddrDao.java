package whut.dao;

import java.util.List;

import whut.pojo.UserAddr;

public interface UserAddrDao {

	List<UserAddr> getListByUserId(int id);

	//通过地址id获取地址完整信息
	UserAddr getAddrByAddrId(Integer userAddrId);

	//修改某用户的使用收货地址为不默认
	void modifyDefault(Integer userAddrId);

	//修改地址信息
	void modifyAddr(UserAddr userAddr);

	//新增收货地址
	void addAddr(UserAddr userAddr);

	//通过地址id删除地址
	void delete(int userAddrId);
	
	
}
