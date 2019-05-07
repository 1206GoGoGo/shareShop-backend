package whut.service;

import whut.utils.ResponseData;

public interface MemberCollectService {

	ResponseData getListByUser();

	ResponseData getAmountById(int id);

	ResponseData add(String jsonString);

	ResponseData delete(String jsonString);


}
