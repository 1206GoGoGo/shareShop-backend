package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.CouponInfo;
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
	public @ResponseBody ResponseData getList(int pageindex, int pagesize) {
		return proCouponService.getList(pageindex, pagesize);
	}
	
	//管理员添加优惠券
	@RequestMapping(value = "/addCoupon", method = RequestMethod.POST, consumes= "application/json")
	public @ResponseBody ResponseData addCoupon(@RequestBody CouponInfo couponInfo) {
		return proCouponService.addCoupon(couponInfo);
	}
	
	//根据优惠券ID查看优惠券详情
	@RequestMapping(value = "/getCouponDetailById", method = RequestMethod.GET)
	public @ResponseBody ResponseData getCouponDetailById(String id) {
		return proCouponService.getCouponDetailById(id);
	}
	
	//管理员删除优惠券（设置优惠券无效）
	@RequestMapping(value = "/deleteCoupon", method = RequestMethod.GET)
	public @ResponseBody ResponseData deleteCoupon(String id) {
		return proCouponService.deleteCoupon(id);
	}
	
	//根据用户Id查看用户领取的未使用(状态为0)优惠券信息
	@RequestMapping(value = "/getCouponByUId", method = RequestMethod.GET)
	public @ResponseBody ResponseData getCouponByUId(String id,int pageindex, int pagesize) {
		return proCouponService.getCouponByUId(id,pageindex, pagesize);
	}
	
	//查看优惠券消费记录
	@RequestMapping(value = "/getCouponLogsList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getCouponLogsList(int pageindex, int pagesize) {
		return proCouponService.getCouponLogsList(pageindex, pagesize);
	}
	
	//向优惠券消费记录表增加记录
	@RequestMapping(value = "/addCouponLogs", method = RequestMethod.POST, consumes= "application/json")
	public @ResponseBody ResponseData addCouponLogs(@RequestBody CouponLogs couponLogs) {
		return proCouponService.addCouponLogs(couponLogs);
	}
	
	
	//根据优惠券id或订单编号查看优惠券消费记录详情
	@RequestMapping(value = "/getCouponLogsDetail", method = RequestMethod.GET)
	public @ResponseBody ResponseData getCouponLogsDetail(String id,String orderNumber) {
		return proCouponService.getCouponLogsDetail(id, orderNumber);
	}
	
	//根据状态获取优惠券消费记录列表
	@RequestMapping(value = "/getCouponLogsListByStatus", method = RequestMethod.GET)
	public @ResponseBody ResponseData getCouponLogsListByStatus(String status,int pageindex, int pagesize) {
		return proCouponService.getCouponLogsListByStatus(status,pageindex, pagesize);
	}
	
	//根据优惠券id修改优惠券消费记录状态失效（使用优惠券但退款）
	@RequestMapping(value = "/modifyCouponLogsStatus", method = RequestMethod.GET)
	public @ResponseBody ResponseData modifyCouponLogsStatus(String id) {
		return proCouponService.modifyCouponLogsStatus(id);
	}
	
	//查看优惠券领取记录
	@RequestMapping(value = "/getCouponReceiveList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getCouponReceiveList(int pageindex, int pagesize) {
		return proCouponService.getCouponReceiveList(pageindex, pagesize);
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
