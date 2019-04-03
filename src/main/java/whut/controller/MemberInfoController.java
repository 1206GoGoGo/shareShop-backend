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
	 * 通过用户状态获取会员信息列表
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getList(int status,int pageindex, int pagesize) {
		return  memberInfoService.getList(status, pageindex, pagesize);
	}
	
	/**
	 * 通过seller获取用户信息列表
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/getListBySeller", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListBySeller(int pagesize, int pageindex, String username) {
		return  memberInfoService.getMemberListBySeller(pagesize, pageindex, username);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody ResponseData add(@RequestBody UserInfo user){
		return  memberInfoService.add(user);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public @ResponseBody ResponseData modify(@RequestBody UserInfo user) {
		return  memberInfoService.modify(user);
	}
	
	/**
	 * 删除该会员（改状态）
	 * 如果需要真正删除，需要对订单表、地址表等数据进行处理
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseData delete(@RequestBody String jsonString) {
		return  memberInfoService.delete(jsonString);
	}
	
	/**
	 * 根据某些字段去查找
	 * @param jsonString查询条件用Post组成json字符串传入
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody ResponseData search(int pagesize,int pageindex,String username,
			String phoneNumber,String name,String identityCardNo,String level,int status, String email) {
		return  memberInfoService.search(pagesize,pageindex,username, phoneNumber, name, identityCardNo,level,status,email);
	}
	
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET)
	public @ResponseBody ResponseData getDetail(int id) {
		return  memberInfoService.getDetail(id);
	}
	
	@RequestMapping(value = "/getCountAWeek", method = RequestMethod.GET)
	public @ResponseBody ResponseData getCountAWeek() {
		return  memberInfoService.getCountAWeek();
	}

}
