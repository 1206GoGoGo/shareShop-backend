package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.UserAddr;
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
	
	@RequestMapping(value = "/getAddrByAddrId", method = RequestMethod.GET)
	public @ResponseBody ResponseData getAddrByAddrId(int addrId) {
		return  memberAddressService.getAddrByAddrId(addrId);
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
	public @ResponseBody ResponseData delete(@RequestBody String jsonString) {
		return  memberAddressService.delete(jsonString);
	}
	
	@RequestMapping(value = "/getListState", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListState() {
		return  memberAddressService.getListState();
	}
	
	@RequestMapping(value = "/getListCity", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListCity(int id) {
		return  memberAddressService.getListCity(id);
	}
}
