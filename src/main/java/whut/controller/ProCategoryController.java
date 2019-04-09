package whut.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.service.ProCategoryService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/pro/category")
public class ProCategoryController {
	
	@Autowired
	public ProCategoryService proCategoryService;
	
	//获取第一层级分类列表
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getList() {
		return proCategoryService.getList();
	}
	
	//根据父分类ID获取子分类列表
	@RequestMapping(value = "/getListByParentId", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByParentId(String id) {
		return proCategoryService.getListByParentId(id);
	}
	
}
