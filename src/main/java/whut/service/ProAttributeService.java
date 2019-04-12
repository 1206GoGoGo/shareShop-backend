package whut.service;


import whut.utils.ResponseData;

public interface ProAttributeService {

	ResponseData getProductAttributeKeyList(int pageindex, int pagesize);

	ResponseData getProductAttributeKeyByIdAndName(String id, String name);

	ResponseData getProductAttributeKeyByCategoryID(String id);

	ResponseData getProductAttributeValueByKeyID(String id);

	ResponseData getProductAttributeValueByIdAndValue(String id, String value);

}
