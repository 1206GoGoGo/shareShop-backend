package whut.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	//获取所有商品列表
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public @ResponseBody ResponseData update() {
		return proRecommendService.updateSolrData();
	}
	
	
}
	

