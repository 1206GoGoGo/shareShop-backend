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
	private ProDiscountService proDiscountService;
	
	//获取商品折扣列表
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getList(Integer pageindex, Integer pagesize) {
		return proDiscountService.getList(pageindex,pagesize);
		
	}
	
	//根据商品分类id查询折扣
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody ResponseData search(String id){
		return proDiscountService.search(id);
	}
	
	//根据商品id查询商品的折扣率并返回，先查询商品信息表--再到商品折扣表，折扣表中如果分类id为0则表示全场打折
	@RequestMapping(value = "/getDiscountRateById", method = RequestMethod.GET)
	public @ResponseBody Integer getDiscountRateById(String id){
		return proDiscountService.getDiscountRateById(id);
	}
	
	//根据商品id查询收益率
	@RequestMapping(value = "/getYieldRateById", method = RequestMethod.GET)
	public @ResponseBody Integer getYieldRateById(String id){
		return proDiscountService.getYieldRateById(id);
	}
	
}
