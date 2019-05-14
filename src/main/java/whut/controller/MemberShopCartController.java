package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.OrderCart;
import whut.service.MemberShopCartService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/member/shopcart")
public class MemberShopCartController {
	@Autowired
	private MemberShopCartService memberShopCartService;
	
	/**
	 * 获取某用户购物车内容
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getListByUser", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByUser() {
		return  memberShopCartService.getListByUser();
	}
	
	/**
	 * 获取某商品加入购物车的总数
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getAmountById", method = RequestMethod.GET)
	public @ResponseBody ResponseData getAmountById(int id) {
		return  memberShopCartService.getAmountById(id);
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseData delete(@RequestBody String jsonString) {
		return  memberShopCartService.delete(jsonString);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public @ResponseBody ResponseData modify(@RequestBody OrderCart orderCart) {
		return  memberShopCartService.modify(orderCart);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody ResponseData add(@RequestBody OrderCart orderCart) {
		return  memberShopCartService.add(orderCart);
	}
}
