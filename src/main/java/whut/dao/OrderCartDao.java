package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.OrderCart;

public interface OrderCartDao {

	List<OrderCart> getListByUser(int id);

	int getAmountById(int id);

	//通过cartId获取OrderCart
	OrderCart getCartByCartId(int cartId);

	//通过cartId删除
	void delete(int cartId);

	//修改购物车信息
	void modify(OrderCart orderCartOld);

	//通过productSpecsId和userId查询购物车
	OrderCart getCartBySpecsId(Map<String, Integer> map);

	//添加购物车信息
	void addCart(OrderCart orderCart);
	
	

}