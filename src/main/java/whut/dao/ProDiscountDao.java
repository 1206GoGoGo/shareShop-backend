package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.ProductDiscount;

public interface ProDiscountDao {

	//获取商品折扣列表
	public List<ProductDiscount> getList(Map<String, Object> map);

	//根据商品分类id查询折扣
	public ProductDiscount search(String id);

	public void add(ProductDiscount productDiscount);

	public void modify(ProductDiscount productDiscount);

}
