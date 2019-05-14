package whut.service;

import org.springframework.web.bind.annotation.RequestBody;

import whut.pojo.OrderCart;
import whut.utils.ResponseData;

public interface MemberShopCartService {

	public ResponseData getListByUser();

	public ResponseData getAmountById(int id);

	public ResponseData delete(@RequestBody String jsonString);

	public ResponseData modify(OrderCart orderCart);

	public ResponseData add(OrderCart orderCart);
}
