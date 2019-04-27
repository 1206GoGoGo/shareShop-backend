package whut.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.service.ProInfoService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/pro/info")
public class ProInfoController {
	
	@Autowired
	private ProInfoService proInfoService;	
	
	//获取所有商品列表
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getList(int pageindex, int pagesize) {
		return proInfoService.getList(pageindex, pagesize);

	}
	
	//根据商品id获取某商品详情
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET)
	public @ResponseBody ResponseData getDetail(String id){
		return proInfoService.getDetail(id);
		
	}
	
	//根据商品码id获取某商品详情
	@RequestMapping(value = "/getDetailByCode", method = RequestMethod.GET)
	public @ResponseBody ResponseData getDetailByCode(String id){
		return proInfoService.getDetailByCode(id);
	}
	
	//根据商品名称通过搜索服务区查找商品
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody ResponseData search(String name,Integer pageindex, Integer pagesize){
		return proInfoService.search(name,pageindex, pagesize);
	}
		
	//根据分类获取商品列表
	@RequestMapping(value = "/getListByCategory", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByCategory(String id,int pageindex, int pagesize){
		return proInfoService.getListByCategory(id,pageindex, pagesize);	
	}
	
}
	

