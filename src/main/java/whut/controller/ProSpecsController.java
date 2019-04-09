package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.service.ProSpecsService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/pro/specs")
public class ProSpecsController {
	
	@Autowired
	private ProSpecsService proSpecsService;
	
	//根据商品id查看商品规格表【getProSpecsByProId】get【String id】
	@RequestMapping(value = "getProSpecsByProId", method = RequestMethod.GET)
	public @ResponseBody ResponseData getProSpecsByProId(String id) {
		return proSpecsService.getProSpecsByProId(id);
	}
	
	//根据商品规格id查看商品规格表【getProSpecsById】get【String id】
	@RequestMapping(value = "getProSpecsById", method = RequestMethod.GET)
	public @ResponseBody ResponseData getProSpecsById(String id) {
		return proSpecsService.getProSpecsById(id);
	}
	
}
