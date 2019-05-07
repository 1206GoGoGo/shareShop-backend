package whut.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import whut.pojo.StateTax;

public interface StateTaxDao {

	//查看所有州的税率【getStateTaxList】
	List<StateTax> getStateTaxList(Map<String, Integer> map);

	//根据州税的id查看对应州的税率【getStateTaxById】
	StateTax getStateTaxById(String id);

	//根据州的名称查看对应州的税率【getStateTaxByName】
	List<StateTax> getStateTaxByName(@Param(value = "name") String name);

}
