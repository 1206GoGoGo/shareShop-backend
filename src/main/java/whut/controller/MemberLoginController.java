package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.UserInfo;
import whut.service.MemberLoginService;
import whut.service.MemberInfoService;
import whut.utils.ResponseData;
import whut.utils.SysContent;

@Controller
@RequestMapping(value = "/member/login")
public class MemberLoginController {
	@Autowired
	private MemberLoginService loginService;

	@Autowired
	private MemberInfoService memberInfoService;
	
	@RequestMapping(value = "/in", method = RequestMethod.POST)
	public @ResponseBody ResponseData loginin(@RequestBody String jsonString) {
		return  loginService.loginin(jsonString);
	}
	
	@RequestMapping(value = "/out", method = RequestMethod.GET)
	public @ResponseBody ResponseData loginout() {
		
		return  loginService.loginout();
	}
	
	@RequestMapping(value = "/getPhoneCode", method = RequestMethod.GET)
	public @ResponseBody ResponseData getPhoneCode(String phoneCode) {
		
		SysContent.getSession().getAttribute("userId");
		
		return  loginService.getPhoneCode(phoneCode);
	}
	
	@RequestMapping(value = "/getMailCode", method = RequestMethod.GET)
	public @ResponseBody ResponseData getMailCode(String mailCode) {
		return  loginService.getMailCode(mailCode);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody ResponseData add(@RequestBody UserInfo user){
		return  memberInfoService.add(user);
	}
}
