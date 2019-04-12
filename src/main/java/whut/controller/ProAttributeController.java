package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import whut.service.ProAttributeService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/pro/attribute")
public class ProAttributeController {
	
	@Autowired
	private ProAttributeService proAttributeService;
	
	//查询商品属性Key表
	@RequestMapping(value = "/getProductAttributeKeyList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getProductAttributeKeyList(int pageindex, int pagesize) {
		return proAttributeService.getProductAttributeKeyList(pageindex, pagesize);
	}
		
	//根据商品分类Id和属性名称查找商品属性Key表
	@RequestMapping(value = "/getProductAttributeKeyByIdAndName", method = RequestMethod.GET)
	public @ResponseBody ResponseData getProductAttributeKeyByIdAndName(String id, String name) {
		return proAttributeService.getProductAttributeKeyByIdAndName(id, name);
	}
	
	//根据商品分类ID查看商品属性Key表
	@RequestMapping(value = "/getProductAttributeKeyByCategoryID", method = RequestMethod.GET)
	public @ResponseBody ResponseData getProductAttributeKeyByCategoryID(String id) {
		return proAttributeService.getProductAttributeKeyByCategoryID(id);
	}
	
	//根据商品属性KeyID查看商品属性值
	@RequestMapping(value = "/getProductAttributeValueByKeyID", method = RequestMethod.GET)
	public @ResponseBody ResponseData getProductAttributeValueByKeyID(String id) {
		return proAttributeService.getProductAttributeValueByKeyID(id);
	}	
	
	//根据属性keyID和属性值查找商品属性Value表
	@RequestMapping(value = "/getProductAttributeValueByIdAndValue", method = RequestMethod.GET)
	public @ResponseBody ResponseData getProductAttributeValueByIdAndValue(String id, String value) {
		return proAttributeService.getProductAttributeValueByIdAndValue(id, value);
	}
	
}
