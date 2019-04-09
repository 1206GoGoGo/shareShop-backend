package whut.service;

import whut.utils.ResponseData;

public interface ProSpecsService {

	ResponseData getProSpecsByProId(String id);

	ResponseData getProSpecsById(String id);

}
