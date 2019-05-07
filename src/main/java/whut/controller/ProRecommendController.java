package whut.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.service.ProRecommendService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/pro/recomm")
public class ProRecommendController {
	
	@Autowired
	private ProRecommendService proRecommendService;
	
	//首页推荐商品列表
	@RequestMapping(value = "/recomm", method = RequestMethod.GET)
	public @ResponseBody ResponseData recomm(Integer pageindex, Integer pagesize) {
		return proRecommendService.recomm(pageindex, pagesize);
	}
	
	//详情页商品列表
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public @ResponseBody ResponseData detail(Integer proid) {
		return proRecommendService.detail(proid);
	}
	
	//搜索页商品列表
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody ResponseData search(Integer pageindex, Integer pagesize, String keyWord) {
		return proRecommendService.search(pageindex, pagesize, keyWord);
	}
	
	//购物车页商品列表
	@RequestMapping(value = "/shopcart", method = RequestMethod.GET)
	public @ResponseBody ResponseData shopcart(Integer pageindex, Integer pagesize) {
		return proRecommendService.shopcart(pageindex, pagesize);
	}
	
}
	

