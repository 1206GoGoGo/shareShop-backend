package whut.dao;

import java.util.List;

import whut.pojo.ProductSpecs;

public interface ProSpecsDao {

	public ProductSpecs getProSpecsBySpecsId(Integer productSpecsId);
	
	//根据商品id查看商品规格表
	public List<ProductSpecs> getProSpecsByProId(String id);

	//根据商品规格id查看商品规格表
	public ProductSpecs getProSpecsById(String id);

	//向商品规格表添加商品规格
	public void addProSpecs(ProductSpecs productSpecs);

	//修改商品规格表的商品规格
	public void modifyProSpecs(ProductSpecs productSpecs);

}
