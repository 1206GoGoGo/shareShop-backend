package whut.dao;

import java.util.List;

import whut.pojo.ProductCategory;

public interface ProCategoryDao {

	//获取第一层级分类列表(status=1)
	public List<ProductCategory> getList();

	//根据分类编码获取商品分类信息
	public ProductCategory ifCategoryExist(String categoryCode);
	
	//根据父分类id获取商品分类信息
	public List<ProductCategory> ifHaveChild(String id);

}
