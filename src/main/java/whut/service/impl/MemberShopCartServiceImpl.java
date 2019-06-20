package whut.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import whut.dao.OrderCartDao;
import whut.dao.ProSpecsDao;
import whut.pojo.OrderCart;
import whut.service.MemberShopCartService;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;
import whut.utils.SysContent;
@Service
public class MemberShopCartServiceImpl implements MemberShopCartService {

	@Autowired
	private OrderCartDao dao;
	
	@Autowired
	private ProSpecsDao proSpecsDao;

	@Override
	public ResponseData getListByUser() {
		List<OrderCart> list = dao.getListByUser(SysContent.getUserId());
		if(list.isEmpty()) {
			return new ResponseData(400,"no data satify request",null);
		}else {
			return new ResponseData(200,"success",list);
		}
	}

	@Override
	public ResponseData getAmountById(int id) {
		return new ResponseData(200,"success",dao.getAmountById(id));
	}

	@Override
	public ResponseData delete(@RequestBody String jsonString) {
		JsonUtils jsonUtils = new JsonUtils(jsonString);
		int cartId = jsonUtils.getIntValue("cartId");
		
		OrderCart orderCart = dao.getCartByCartId(cartId);
		if(orderCart==null) {
			return new ResponseData(406,"the cart_goods does not exist",null);
		}
		if(orderCart.getUserId()!=SysContent.getUserId()) {
			return new ResponseData(403,"illegally accessed",null);
		}
		dao.delete(cartId);
		return new ResponseData(null);
	}

	@Override
	public ResponseData modify(OrderCart orderCart) {
		//获取原来购物车信息
		OrderCart orderCartOld = dao.getCartByCartId(orderCart.getCartId());
		if(orderCartOld==null) {
			return new ResponseData(406,"the cart_goods does not exist",null);
		}
		if(orderCartOld.getUserId()!=SysContent.getUserId()) {
			return new ResponseData(403,"illegally accessed",null);
		}
		
		//判断购物车内是否存在已有商品
		Map<String, Integer> map = new HashMap<>();
		map.put("productSpecsId", orderCart.getProductSpecsId());
		map.put("userId", SysContent.getUserId());
		OrderCart orderCartOldJust = dao.getCartBySpecsId(map);
		if(orderCartOldJust != null) {
			if(orderCartOldJust.getCartId()!=orderCart.getCartId()) {
				//存在该商品的其它规格
				orderCartOldJust.setProductQuantity(orderCartOldJust.getProductQuantity()+orderCart.getProductQuantity());
				dao.modify(orderCartOldJust);
				dao.delete(orderCart.getCartId());
				return new ResponseData(200,"existing, integrated shopping cart",null);
			}
		}
		
		orderCartOld.setProductQuantity(orderCart.getProductQuantity());
		orderCartOld.setProductSpecsId(orderCart.getProductSpecsId());
		orderCartOld.setProductPrice( (proSpecsDao.getProSpecsById(orderCart.getProductSpecsId().toString())).getPrice() );
		dao.modify(orderCartOld);
		return new ResponseData(null);
	}

	@Override
	public ResponseData add(OrderCart orderCart) {
		Map<String, Integer> map = new HashMap<>();
		map.put("productSpecsId", orderCart.getProductSpecsId());
		map.put("productId", orderCart.getProductId());
		map.put("userId", SysContent.getUserId());
		OrderCart orderCartOld = dao.getCartBySpecsId(map);
		if(orderCartOld != null) {
			//购物车已有该商品，数量加一
			orderCartOld.setProductQuantity(orderCartOld.getProductQuantity()+1);
			dao.modify(orderCartOld);
			return new ResponseData(200,"the cart_goods does exist, quantity+1",null);
		}
		orderCart.setUserId(SysContent.getUserId());
		orderCart.setAddTime(new java.util.Date());
		orderCart.setProductPrice( (proSpecsDao.getProSpecsById(orderCart.getProductSpecsId().toString())).getPrice() );
		dao.addCart(orderCart);
		return new ResponseData(null);
	}

}
