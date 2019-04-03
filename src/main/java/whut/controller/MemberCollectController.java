package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.service.MemberCollectService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/member/collect")
public class MemberCollectController {
	@Autowired
	private MemberCollectService memberCollectService;
	
	/**
	 * 通过用户id获取收藏列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getListByUser", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByUser(int id) {
		return memberCollectService.getListByUser(id);
	}
	
	/**
	 * 通过商品id获取收藏数量
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getAmountById", method = RequestMethod.GET)
	public @ResponseBody ResponseData getAmountById(int id) {
		return  memberCollectService.getAmountById(id);
	}
	
}
