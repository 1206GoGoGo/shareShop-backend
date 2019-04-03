package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.ProductAttributeKey;
import whut.pojo.ProductAttributeValue;
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
	
	//向商品属性Key表增加商品属性Key(添加之前判断，商品分类Id和商品属性不能同时与之前相同)
	@RequestMapping(value = "/addProductAttributeKey", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseData addProductAttributeKey(@RequestBody ProductAttributeKey productAttributeKey) {
		return proAttributeService.addProductAttributeKey(productAttributeKey);
	}
	
	//根据商品分类Id和属性名称查找商品属性Key表
	@RequestMapping(value = "/getProductAttributeKeyByIdAndName", method = RequestMethod.GET)
	public @ResponseBody ResponseData getProductAttributeKeyByIdAndName(String id, String name) {
		return proAttributeService.getProductAttributeKeyByIdAndName(id, name);
	}
	
	//修改商品属性Key表信息(修改之前判断，商品分类Id和商品属性不能同时与之前相同)
	@RequestMapping(value = "/modifyProductAttributeKey", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseData modifyProductAttributeKey(@RequestBody ProductAttributeKey productAttributeKey) {
		return proAttributeService.modifyProductAttributeKey(productAttributeKey);
	}
	
	//根据属性KeyID改变商品属性Key表信息的状态
	@RequestMapping(value = "/modifyProductAttributeKeyByStatus", method = RequestMethod.GET)
	public @ResponseBody ResponseData modifyProductAttributeKeyByStatus(String id,String status) {
		return proAttributeService.modifyProductAttributeKeyByStatus(id, status);
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
	
	//向商品属性Value表增加商品属性Value(添加之前判断，属性keyID和属性值不能同时与之前相同)
	@RequestMapping(value = "/addProductAttributeValue", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseData addProductAttributeValue(@RequestBody ProductAttributeValue productAttributeValue) {
		return proAttributeService.addProductAttributeValue(productAttributeValue);
	}
	
	//根据属性keyID和属性值查找商品属性Value表
	@RequestMapping(value = "/getProductAttributeValueByIdAndValue", method = RequestMethod.GET)
	public @ResponseBody ResponseData getProductAttributeValueByIdAndValue(String id, String value) {
		return proAttributeService.getProductAttributeValueByIdAndValue(id, value);
	}
	
	//修改商品属性Value表信息
	@RequestMapping(value = "/modifyProductAttributeValue", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseData modifyProductAttributeValue(@RequestBody ProductAttributeValue productAttributeValue) {
		return proAttributeService.modifyProductAttributeValue(productAttributeValue);
	}
	
	//根据属性ValueID改变商品属性Value表信息状态
	@RequestMapping(value = "/modifyProductAttributeValueByStatus", method = RequestMethod.GET)
	public @ResponseBody ResponseData modifyProductAttributeValueByStatus(String id, String status) {
		return proAttributeService.modifyProductAttributeValueByStatus(id, status);
	}
}
