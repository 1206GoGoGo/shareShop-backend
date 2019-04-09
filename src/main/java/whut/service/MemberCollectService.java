package whut.service;

import whut.utils.ResponseData;

public interface MemberCollectService {

	ResponseData getListByUser();

	ResponseData getAmountById(int id);


}
