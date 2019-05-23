package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.OrderMaster;
import whut.service.MemberOrderService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/member/order")
public class MemberOrderController {
	@Autowired
	private MemberOrderService memberOrderService;
	
	/**
	 * 根据订单状态
	 * @param Status
	 * @return
	 */
	@RequestMapping(value = "/getListByStatus", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByStatus(Integer pageindex, Integer pagesize, Integer status) {
		return memberOrderService.getListByStatus(pageindex, pagesize, status);
	}

	/**
	 * 获取完整的一个订单信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET)
	public @ResponseBody ResponseData getDetail(int id) {
		return memberOrderService.getDetail(id);
	}
	
	//暂时不实现
	@RequestMapping(value = "/getDeliveryInfo", method = RequestMethod.GET)
	public @ResponseBody ResponseData getDeliveryInfo(int id) {
		return  new ResponseData(400, "success", null);
	}
	

	/**
	 * 修改订单的物流等信息
	 * @param orderMaster
	 * @return
	 */
	@RequestMapping(value = "/modifyOrder", method = RequestMethod.POST)
	public @ResponseBody ResponseData modifyOrder(@RequestBody OrderMaster orderMaster) {
		//return memberOrderService.modifyOrder(orderMaster);
		return null;
	}
	
	/**
	 * 获取某用户的消费记录
	 * @param pageindex
	 * @param pagesize
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getRecord", method = RequestMethod.GET)
	public @ResponseBody ResponseData getRecordByUser(Integer pageindex, Integer pagesize, String timebe, String timeen) {
		return memberOrderService.getRecordByUser(pageindex, pagesize, timebe, timeen);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseData delete(@RequestBody String jsonString) {
		return memberOrderService.delete(jsonString);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody ResponseData add(@RequestBody String jsonString) {
		return memberOrderService.add(jsonString);
	}
	
	@RequestMapping(value = "/modifyPay", method = RequestMethod.POST)
	public @ResponseBody ResponseData modifyPay(@RequestBody String jsonString) {
		return memberOrderService.modifyPay(jsonString);
	}
	
	@RequestMapping(value = "/receipt", method = RequestMethod.POST)
	public @ResponseBody ResponseData receipt(@RequestBody String jsonString) {
		return memberOrderService.receipt(jsonString);
	}
	
	/**
	 * 整个订单退货（款）
	 * @param jsonString
	 * @return
	 */
	@RequestMapping(value = "/applyReturnForOrder", method = RequestMethod.POST)
	public @ResponseBody ResponseData applyReturnForOrder(@RequestBody String jsonString) {
		return memberOrderService.applyReturnForOrder(jsonString);
	}
	
	@RequestMapping(value = "/applyReturnForDetail", method = RequestMethod.POST)
	public @ResponseBody ResponseData applyReturnForDetail(@RequestBody String jsonString) {
		return memberOrderService.applyReturnForDetail(jsonString);
	}

}
