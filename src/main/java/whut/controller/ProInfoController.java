package whut.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.ProductInfo;
import whut.service.ProInfoService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/pro/info")
public class ProInfoController {
	
	@Autowired
	public ProInfoService proInfoService;
	
	
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
	
	//添加新商品信息
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes= "application/json")
	public @ResponseBody ResponseData add(@RequestBody ProductInfo productInfo){		
		return proInfoService.add(productInfo);
	}
	
	//根据商品名称查找商品
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody ResponseData search(String name,int pageindex, int pagesize){
		return proInfoService.search(name,pageindex, pagesize);
	}
	
	//修改某商品属性
	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes= "application/json")
	public @ResponseBody ResponseData modify(@RequestBody ProductInfo productInfo){
		return proInfoService.modify(productInfo);
		
	}
	
	//根据商品id修改某商品审核状态
	@RequestMapping(value = "/modifyAuditStatus", method = RequestMethod.GET)
	public @ResponseBody ResponseData modifyAuditStatus(String id, String status){
		return proInfoService.modifyAuditStatus(id, status);
		
	}
	
	//根据商品id修改某商品上下架状态
	@RequestMapping(value = "/modifyShelfStatus", method = RequestMethod.GET)
	public @ResponseBody ResponseData modifyShelfStatus(String id, String status){
		return proInfoService.modifyShelfStatus(id, status);
		
	}
	
	//根据分类获取商品列表
	@RequestMapping(value = "/getListByCategory", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByCategory(String id,int pageindex, int pagesize){
		return proInfoService.getListByCategory(id,pageindex, pagesize);	
	}
	
	//根据审核状态获取所有的商品列表
	@RequestMapping(value = "/getIfcheckedList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getIfcheckedList(String status,int pageindex, int pagesize){
		return proInfoService.getIfcheckedList(status,pageindex, pagesize);	
	}
	
	//根据上下架状态获取所有的商品列表
	@RequestMapping(value = "/getIfShelfList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getIfShelfList(String status,int pageindex, int pagesize){
		return proInfoService.getIfShelfList(status,pageindex, pagesize);	
	}
	
	//管理员联合查询
	@RequestMapping(value = "/getListByCondition", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByCondition(String name, String procode, String categoryId1, String categoryId2,String categoryId3,String status1,String status2,int pageindex, int pagesize){
		return proInfoService.getListByCondition(name,procode,categoryId1,categoryId2,categoryId3,status1,status2,pageindex, pagesize);	
	}
	
}
	

