package whut.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.service.ManagerLoginService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/manager/login")
public class ManagerLoginController {
	@Autowired
	private ManagerLoginService managerLoginService;
	
	@RequestMapping(value = "/in", method = RequestMethod.POST)
	public @ResponseBody ResponseData loginin(@RequestBody String jsonString, HttpServletRequest request, HttpServletResponse response) {
		return  managerLoginService.loginin(jsonString, request, response);
	}
	
	
	@RequestMapping(value = "/out", method = RequestMethod.GET)
	public @ResponseBody ResponseData loginout(String username, HttpServletRequest request, HttpServletResponse response) {
		return  managerLoginService.loginout(username, request, response);
	}
	
	@RequestMapping(value = "/getPhoneCode", method = RequestMethod.GET)
	public @ResponseBody ResponseData getPhoneCode(String phoneCode) {
		return  managerLoginService.getPhoneCode(phoneCode);
	}
	
	@RequestMapping(value = "/getMailCode", method = RequestMethod.GET)
	public @ResponseBody ResponseData getMailCode(String mailCode) {
		return  managerLoginService.getMailCode(mailCode);
	}
}
