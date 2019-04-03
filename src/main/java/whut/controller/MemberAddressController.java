package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.service.MemberAddressService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/member/address")
public class MemberAddressController {
	
	@Autowired
	private MemberAddressService memberAddressService;
	
	@RequestMapping(value = "/getListByUser", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByUserId(int id) {
		return  memberAddressService.getListByUserId(id);
	}
	
}
