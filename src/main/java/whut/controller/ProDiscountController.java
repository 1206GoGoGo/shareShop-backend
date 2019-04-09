package whut.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.service.ProDiscountService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/pro/discount")
public class ProDiscountController {
	
	@Autowired
	public ProDiscountService proDiscountService;
	
	//获取商品折扣列表
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getList(int pageindex, int pagesize) {
		return proDiscountService.getList(pageindex,pagesize);
		
	}
	
	//根据商品分类id查询折扣
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody ResponseData search(String id){
		return proDiscountService.search(id);
	}
	
}
