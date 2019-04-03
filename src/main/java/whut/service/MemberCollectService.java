package whut.service;

import whut.utils.ResponseData;

public interface MemberCollectService {

	ResponseData getListByUser(int id);

	ResponseData getAmountById(int id);


}
