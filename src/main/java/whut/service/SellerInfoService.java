package whut.service;

import whut.utils.ResponseData;

public interface SellerInfoService {

	ResponseData getSellerList(int pageindex, int pagesize);

	ResponseData addSeller(String id);

	ResponseData deleteSeller(String id);

}
