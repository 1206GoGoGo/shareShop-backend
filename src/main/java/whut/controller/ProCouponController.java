package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import whut.pojo.CouponLogs;
import whut.pojo.CouponReceive;
import whut.service.ProCouponService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/pro/coupon")
public class ProCouponController {

	@Autowired
	private ProCouponService proCouponService;
	
	//获取所有优惠券信息
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getList(Integer pageindex, Integer pagesize) {
		return proCouponService.getList(pageindex, pagesize);
	}
	
	
	//根据优惠券ID查看优惠券详情
	@RequestMapping(value = "/getCouponDetailById", method = RequestMethod.GET)
	public @ResponseBody ResponseData getCouponDetailById(String id) {
		return proCouponService.getCouponDetailById(id);
	}
	
	//根据用户Id查看用户领取的未使用(状态为0)优惠券信息
	@RequestMapping(value = "/getCouponByUId", method = RequestMethod.GET)
	public @ResponseBody ResponseData getCouponByUId(String id,Integer pageindex, Integer pagesize) {
		return proCouponService.getCouponByUId(pageindex, pagesize);
	}
	
	//向优惠券消费记录表增加记录
	@RequestMapping(value = "/addCouponLogs", method = RequestMethod.POST, consumes= "application/json")
	public @ResponseBody ResponseData addCouponLogs(@RequestBody CouponLogs couponLogs) {
		return proCouponService.addCouponLogs(couponLogs);
	}
	
	
	//根据状态获取优惠券消费记录列表
	@RequestMapping(value = "/getCouponLogsListByStatus", method = RequestMethod.GET)
	public @ResponseBody ResponseData getCouponLogsListByStatus(String status,Integer pageindex, Integer pagesize) {
		return proCouponService.getCouponLogsListByStatus(status,pageindex, pagesize);
	}

	//向优惠券领取表增加记录
	@RequestMapping(value = "/addCouponReceive", method = RequestMethod.POST, consumes= "application/json")
	public @ResponseBody ResponseData addCouponReceive(@RequestBody CouponReceive couponReceive) {
		return proCouponService.addCouponReceive(couponReceive);
	}
	
	//根据优惠券id修改优惠券领取表状态
	@RequestMapping(value = "/modifyCouponReceiveStatus", method = RequestMethod.GET)
	public @ResponseBody ResponseData modifyCouponReceiveStatus(String id) {
		return proCouponService.modifyCouponReceiveStatus(id);
	}
	
	
}
