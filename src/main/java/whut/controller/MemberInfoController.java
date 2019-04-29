package whut.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.UserInfo;
import whut.service.MemberInfoService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/member/info")
public class MemberInfoController {
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	
	/**
	 * 通过seller获取用户信息列表
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/getListBySeller", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListBySeller(Integer pagesize, Integer pageindex, Integer status) {
		return  memberInfoService.getMemberListBySeller(pagesize, pageindex, status);
	}
	
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public @ResponseBody ResponseData modify(@RequestBody UserInfo user) {
		return  memberInfoService.modify(user);
	}
	
	@RequestMapping(value = "/modifyPhone", method = RequestMethod.POST)
	public @ResponseBody ResponseData modifyPhone(@RequestBody String jsonString) {
		return  memberInfoService.modifyPhone(jsonString);
	}

	@RequestMapping(value = "/modifyEmail", method = RequestMethod.POST)
	public @ResponseBody ResponseData modifyEmail(@RequestBody String jsonString) {
		return  memberInfoService.modifyEmail(jsonString);
	}

	@RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
	public @ResponseBody ResponseData modifyPassword(@RequestBody String jsonString) {
		return  memberInfoService.modifyPassword(jsonString);
	}	
	/**
	 * 删除该会员（改状态）
	 * 如果需要真正删除，需要对订单表、地址表等数据进行处理
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseData delete() {
		return  memberInfoService.delete();
	}
	
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET)
	public @ResponseBody ResponseData getDetail() {
		return  memberInfoService.getDetail();
	}
	
	@RequestMapping(value = "/getCountAWeek", method = RequestMethod.GET)
	public @ResponseBody ResponseData getCountAWeek() {
		return  memberInfoService.getCountAWeek();
	}

}
