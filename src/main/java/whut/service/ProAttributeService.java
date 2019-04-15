package whut.service;


import whut.utils.ResponseData;

public interface ProAttributeService {

	ResponseData getProductAttributeKeyByCategoryID(String id);

	ResponseData getProductAttributeValueByKeyID(String id);

}
