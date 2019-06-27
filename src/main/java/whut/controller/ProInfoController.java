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
	public @ResponseBody ResponseData getList(Integer pageindex, Integer pagesize) {
		return proInfoService.getList(pageindex, pagesize);

	}
	
	//获取所有商品搜索服务器的所有商品列表（新增给前台）
	@RequestMapping(value = "/getListSearch", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListSearch(Integer pageindex, Integer pagesize,String field,Byte judge) {
		return proInfoService.getListSearch(pageindex, pagesize,field,judge);

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
	
	//根据商品名称通过搜索服务器solr查找商品（修改给前台）
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody ResponseData search(String name,Integer pageindex, Integer pagesize,String field,Byte judge){
		return proInfoService.search(name,pageindex, pagesize,field,judge);
	}
		
	
	//根据分类获取商品列表
	@RequestMapping(value = "/getListByCategory", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByCategory(String id,Integer pageindex, Integer pagesize){
		return proInfoService.getListByCategory(id,pageindex, pagesize);	
	}
	
	//根据分类通过搜索服务器solr查找商品（新增给前台）
	@RequestMapping(value = "/getListByCategorySearch", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByCategorySearch(String id,Integer pageindex, Integer pagesize,String field,Byte judge){
		return proInfoService.getListByCategorySearch(id,pageindex, pagesize,field,judge);	
	}
	
	//根据商品id获取商品的展示图
	@RequestMapping(value = "/getPicList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getPicList(String id){
		return proInfoService.getPicList(id);	
	}
	
	@RequestMapping(value = "/getRecommendPro", method = RequestMethod.GET)
	public @ResponseBody ResponseData getRecommendPro(){
		return proInfoService.getRecommendPro();	
	}
	
}
	

