package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.ProductAttributeKey;
import whut.pojo.ProductAttributeValue;

public interface ProAttributeDao {


	//根据商品分类ID查看商品属性Key表
	List<ProductAttributeKey> getProductAttributeKeyByCategoryID(String id);
	
	//根据商品属性KeyID查看商品属性值
	List<ProductAttributeValue> getProductAttributeValueByKeyID(String id);

}
