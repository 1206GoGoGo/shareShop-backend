package whut.service;

import whut.utils.ResponseData;

public interface MemberOrderReturnService {

	ResponseData getListByUser(int pageindex, int pagesize);

	ResponseData getListByStatus(int pageindex, int pagesize, int status);

	ResponseData getListByOrderId(int orderId);

	ResponseData getListByReturnType(int pageindex, int pagesize, int type);

	ResponseData addReturn(String jsonString);

}
