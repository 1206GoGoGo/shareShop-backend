package whut.service;

import whut.utils.ResponseData;

public interface MemberShopCartService {

	public ResponseData getListByUser();

	public ResponseData getAmountById(int id);
}
