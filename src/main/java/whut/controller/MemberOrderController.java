package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.OrderDetail;
import whut.pojo.OrderMaster;
import whut.service.MemberOrderService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/member/order")
public class MemberOrderController {
	@Autowired
	private MemberOrderService memberOrderService;
	
	/**
	 * 获取某用户的订单信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getListByUser", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByUser(int pageindex, int pagesize, int id) {
		return memberOrderService.getListByUser(pageindex, pagesize, id);
	}
	
	/**
	 * 根据用户名获取订单列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getListByUserName", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByUserName(int pageindex, int pagesize, String username) {
		return memberOrderService.getListByUserName(pageindex, pagesize, username);
	}

	
	/**
	 * 根据订单状态
	 * @param Status
	 * @return
	 */
	@RequestMapping(value = "/getListByStatus", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByStatus(int pageindex, int pagesize, int status) {
		return memberOrderService.getListByStatus(pageindex, pagesize, status);
	}
	
	/**
	 * 通过订单号查询订单（唯一返回值）
	 * @param orderNumber
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody ResponseData search(String orderNumber) {
		return memberOrderService.search(orderNumber);
	}
	
	/**
	 * 通过订单id获取该订单下的商品列表
	 * @param Status
	 * @return
	 */
	@RequestMapping(value = "/getDetailListByOrderId", method = RequestMethod.GET)
	public @ResponseBody ResponseData getDetailListByOrderId(int orderId) {
		return memberOrderService.getDetailListByOrderId(orderId);
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
		return memberOrderService.modifyOrder(orderMaster);
	}
	
	/**
	 * 修改购买商品的颜色数量等信息
	 * @param orderDetail
	 * @return
	 */
	@RequestMapping(value = "/modifyPro", method = RequestMethod.POST)
	public @ResponseBody ResponseData modifyPro(@RequestBody OrderDetail orderDetail ) {
		return memberOrderService.modifyPro(orderDetail );
	}
	
	/**
	 * 修改整个订单信息（物流等）//订单id
	 * 需要修改下面子商品的信息（退换货等）
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/modifyOrderStatus", method = RequestMethod.POST)
	public @ResponseBody ResponseData modifyOrderStatus(@RequestBody String jsonString) {
		return memberOrderService.modifyOrderStatus(jsonString);
	}
	
	/**
	 * 修改单个商品状态（退货中等）//订单详情id
	 * 需要同步修改整个订单的状态（退换货等）
	 * @param orderDetailId
	 * @return
	 */
	@RequestMapping(value = "/modifyProStatus", method = RequestMethod.POST)
	public @ResponseBody ResponseData modifyProStatus(@RequestBody String jsonString) {
		return memberOrderService.modifyProStatus(jsonString);
	}
	
	/**
	 * 获取某用户的消费记录
	 * @param pageindex
	 * @param pagesize
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getRecord", method = RequestMethod.GET)
	public @ResponseBody ResponseData getRecordByUser(int pageindex, int pagesize, String user, String timebe, String timeen) {
		return memberOrderService.getRecordByUser(pageindex, pagesize, user, timebe, timeen);
	}

}
