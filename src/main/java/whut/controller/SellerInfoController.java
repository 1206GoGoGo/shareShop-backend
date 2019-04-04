package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.service.SellerInfoService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/seller/info")
public class SellerInfoController {
	
	@Autowired
	public SellerInfoService sellerInfoService;
	
	
	//获取所有的seller信息
	@RequestMapping(value = "/getSellerList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getSellerList(int pageindex, int pagesize){
		return sellerInfoService.getSellerList(pageindex,pagesize);	
	}

	//注册seller，修改UserInfo表用户是否是店主
	@RequestMapping(value = "/addSeller", method = RequestMethod.GET)
	public @ResponseBody ResponseData addSeller(String id){
		return sellerInfoService.addSeller(id);	
	}

	//注销seller，修改UserInfo表用户是否是店主
	@RequestMapping(value = "/deleteSeller", method = RequestMethod.GET)
	public @ResponseBody ResponseData deleteSeller(String id){
		return sellerInfoService.deleteSeller(id);	
	}
}
