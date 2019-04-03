package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.service.MemberInfoService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/member/mess")
public class MemberMessController {
	@Autowired
	private MemberInfoService userService;
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public @ResponseBody Object getList() {
		return  new ResponseData(200, "success", null);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public @ResponseBody Object add() {
		return  new ResponseData(200, "success", null);
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public @ResponseBody Object reply() {
		return  new ResponseData(200, "success", null);
	}
}
