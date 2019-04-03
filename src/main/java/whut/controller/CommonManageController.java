package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.ProductDiscount;
import whut.service.CommonManageService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/comm")
public class CommonManageController {
	@Autowired
	public CommonManageService commonManageService;
	
	/**
	 * 获取整个站点商品统一的折扣，包括会员折扣，seller折扣及seller返现率
	 * @param category 校验位必须传0
	 * @return
	 */
	@RequestMapping(value = "/discount/get", method = RequestMethod.GET)
	public @ResponseBody ResponseData getDiscount(int category) {
		return commonManageService.getDiscount(category);
	}
	
	/**
	 * 修改整个站点商品统一的折扣，包括会员折扣，seller折扣及seller返现率
	 * @param productDiscount
	 * @return
	 */
	@RequestMapping(value = "/discount/modify", method = RequestMethod.POST)
	public @ResponseBody ResponseData modifyDiscount(@RequestBody ProductDiscount productDiscount) {
		return commonManageService.modifyDiscount(productDiscount);
	}
	
}
