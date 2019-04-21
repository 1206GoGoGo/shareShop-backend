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
	private SellerInfoService sellerInfoService;
	

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
