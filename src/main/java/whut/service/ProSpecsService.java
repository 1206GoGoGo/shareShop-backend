package whut.service;

import whut.pojo.ProductSpecs;
import whut.utils.ResponseData;

public interface ProSpecsService {

	ResponseData getProSpecsByProId(String id);

	ResponseData getProSpecsById(String id);

	ResponseData addProSpecs(ProductSpecs productSpecs);

	ResponseData modifyProSpecs(ProductSpecs productSpecs);

}
