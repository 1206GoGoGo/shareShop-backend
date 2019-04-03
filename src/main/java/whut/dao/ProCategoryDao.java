package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.ProductCategory;
import whut.utils.ResponseData;

public interface ProCategoryDao {

	//获取第一层级分类列表(status=1)
	public List<ProductCategory> getList();

	//新增分类(默认status=1)
	public void add(ProductCategory productCategory);

	//修改分类
	public void modify(ProductCategory productCategory);

	//删除分类,其下有子分类时判断
	public void delete(String id);

	//根据分类编码获取商品分类信息
	public ProductCategory ifCategoryExist(String categoryCode);
	
	//根据父分类id获取商品分类信息
	public List<ProductCategory> ifHaveChild(String id);
	
	//根据分类id设置导航栏不显示（状态为2）modifyStatusNoShow
	public void modifyStatusNoShow(String id);

	

}
