package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.ProductAttributeKey;
import whut.pojo.ProductAttributeValue;

public interface ProAttributeDao {

	List<ProductAttributeKey> getProductAttributeKeyList(Map<String, Integer> map);

	ProductAttributeKey getProductAttributeKeyByIdAndName(Map<String, String> map);

	void addProductAttributeKey(ProductAttributeKey productAttributeKey);

	void modifyProductAttributeKey(ProductAttributeKey productAttributeKey1);

	List<ProductAttributeKey> getProductAttributeKeyByCategoryID(String id);


	ProductAttributeValue getProductAttributeValueByIdAndValue(Map<String, String> map);

	void addProductAttributeValue(ProductAttributeValue productAttributeValue);

	void modifyProductAttributeValue(ProductAttributeValue productAttributeValue1);

	void modifyProductAttributeKeyByStatus(Map<String, String> map);

	void modifyProductAttributeValueByStatus(Map<String, String> map);

	List<ProductAttributeValue> getProductAttributeValueByKeyID(String id);

}
