package whut.service;

import whut.pojo.CouponLogs;
import whut.pojo.CouponReceive;
import whut.utils.ResponseData;

public interface ProCouponService {

	ResponseData getList(Integer pageindex, Integer pagesize);

	ResponseData getCouponDetailById(String id);

	ResponseData getCouponByUId(String id, Integer pageindex, Integer pagesize);

	ResponseData addCouponLogs(CouponLogs couponLogs);

	ResponseData getCouponLogsListByStatus(String status, Integer pageindex, Integer pagesize);

	ResponseData addCouponReceive(CouponReceive couponReceive);

	ResponseData modifyCouponReceiveStatus(String id);
	
	//获取当前用户领取优惠券数量
	Integer getCouponAmountByUser();

}
