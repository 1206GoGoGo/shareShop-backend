package whut.service;

import whut.utils.ResponseData;

public interface MemberOrderReturnService {

	ResponseData getListByUser(int pageindex, int pagesize, int userId);

	ResponseData getListByStatus(int pageindex, int pagesize, int status);

	ResponseData getListByOrderId(int orderId);

	ResponseData getListByReturnType(int pageindex, int pagesize, int type);

}
