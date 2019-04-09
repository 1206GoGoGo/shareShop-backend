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

	//向商品属性Key表增加商品属性Key
	void addProductAttributeKey(ProductAttributeKey productAttributeKey);

	//修改商品属性Key表信息
	void modifyProductAttributeKey(ProductAttributeKey productAttributeKey1);

	//根据商品分类ID查看商品属性Key表
	List<ProductAttributeKey> getProductAttributeKeyByCategoryID(String id);

	//根据属性keyID和属性值查找商品属性Value表
	ProductAttributeValue getProductAttributeValueByIdAndValue(Map<String, String> map);

	//向商品属性Value表增加商品属性Value
	void addProductAttributeValue(ProductAttributeValue productAttributeValue);

	//修改商品属性Value表信息
	void modifyProductAttributeValue(ProductAttributeValue productAttributeValue1);

	//根据属性KeyID改变商品属性Key表信息的状态
	void modifyProductAttributeKeyByStatus(Map<String, String> map);

	//根据属性ValueID改变商品属性Value表信息状态
	void modifyProductAttributeValueByStatus(Map<String, String> map);

	//根据商品属性KeyID查看商品属性值
	List<ProductAttributeValue> getProductAttributeValueByKeyID(String id);

}
