package whut.dao;

import java.util.List;

import whut.pojo.ManagerCategory;

public interface ManagerCategoryDao {

	List<ManagerCategory> getList();

	void add(ManagerCategory managerCategory);

	void modify(ManagerCategory managerCategory);

	void delete(int id);

	//不存在返回null
	ManagerCategory getIdByName(String name);

	//通过分类id获取level号
	ManagerCategory getByCategoryId(int id);

}
