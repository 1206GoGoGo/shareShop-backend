package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.ProductAttributeKey;
import whut.pojo.ProductAttributeValue;

public interface ProAttributeDao {

	//查询商品属性Key表
	List<ProductAttributeKey> getProductAttributeKeyList(Map<String, Integer> map);

	//根据商品分类Id和属性名称查找商品属性Key表
	ProductAttributeKey getProductAttributeKeyByIdAndName(Map<String, String> map);

	//根据商品分类ID查看商品属性Key表
	List<ProductAttributeKey> getProductAttributeKeyByCategoryID(String id);

	//根据属性keyID和属性值查找商品属性Value表
	ProductAttributeValue getProductAttributeValueByIdAndValue(Map<String, String> map);

	//根据商品属性KeyID查看商品属性值
	List<ProductAttributeValue> getProductAttributeValueByKeyID(String id);

}
