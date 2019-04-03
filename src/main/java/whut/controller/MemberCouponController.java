package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.service.MemberCollectService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/member/coupon")
public class MemberCouponController {
	@Autowired
	private MemberCollectService memberCollectService;
	
	@RequestMapping(value = "/getListByUser", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByUser(int id) {
		//return MemberCollectService.getListByUser(id);
		return null;
	}
	
	@RequestMapping(value = "/getAmountById", method = RequestMethod.GET)
	public @ResponseBody ResponseData modify(int userid, int getcouponid) {
		//return MemberCollectService.getAmountById(userid, getcouponid);
		return null;
	}
	
}
