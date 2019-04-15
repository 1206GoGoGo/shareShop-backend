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
	
}
