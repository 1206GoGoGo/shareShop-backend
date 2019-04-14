package whut.service;

import whut.pojo.OrderCart;
import whut.utils.ResponseData;

public interface MemberShopCartService {

	public ResponseData getListByUser();

	public ResponseData getAmountById(int id);

	public ResponseData delete(int cartId);

	public ResponseData modify(OrderCart orderCart);

	public ResponseData add(OrderCart orderCart);
}
