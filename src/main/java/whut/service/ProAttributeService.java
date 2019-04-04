package whut.service;

import whut.pojo.ProductAttributeKey;
import whut.pojo.ProductAttributeValue;
import whut.utils.ResponseData;

public interface ProAttributeService {

	ResponseData getProductAttributeKeyList(int pageindex, int pagesize);

	ResponseData addProductAttributeKey(ProductAttributeKey productAttributeKey);

	ResponseData getProductAttributeKeyByIdAndName(String id, String name);

	ResponseData modifyProductAttributeKey(ProductAttributeKey productAttributeKey);

	ResponseData modifyProductAttributeKeyByStatus(String id, String status);

	ResponseData getProductAttributeKeyByCategoryID(String id);

	ResponseData getProductAttributeValueByKeyID(String id);

	ResponseData addProductAttributeValue(ProductAttributeValue productAttributeValue);

	ResponseData getProductAttributeValueByIdAndValue(String id, String value);

	ResponseData modifyProductAttributeValue(ProductAttributeValue productAttributeValue);

	ResponseData modifyProductAttributeValueByStatus(String id, String status);

}
