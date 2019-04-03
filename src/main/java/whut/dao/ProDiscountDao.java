package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.ProductDiscount;

public interface ProDiscountDao {

	public List<ProductDiscount> getList(Map<String, Object> map);

	public ProductDiscount search(String id);

	public void add(ProductDiscount productDiscount);

	public void modify(ProductDiscount productDiscount);

}
