package whut.dao;

import java.util.List;

import whut.pojo.ManagerInfo;

public interface ManagerInfoDao {

	List<ManagerInfo> getList(int status);

	void add(ManagerInfo managerInfo);

	ManagerInfo getDetail(int id);

	void modify(ManagerInfo managerInfo);

	void delete(int id);

}
