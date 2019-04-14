package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.UserAddr;
import whut.pojo.UserInfo;
import whut.service.MemberAddressService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/member/address")
public class MemberAddressController {
	
	@Autowired
	private MemberAddressService memberAddressService;
	
	@RequestMapping(value = "/getListByUser", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByUserId() {
		return  memberAddressService.getListByUserId();
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public @ResponseBody ResponseData modify(@RequestBody UserAddr userAddr) {
		return  memberAddressService.modify(userAddr);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody ResponseData add(@RequestBody UserAddr userAddr) {
		return  memberAddressService.add(userAddr);
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseData delete(@RequestBody int userAddrId) {
		return  memberAddressService.delete(userAddrId);
	}
}
