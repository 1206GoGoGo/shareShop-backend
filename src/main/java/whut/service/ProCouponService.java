package whut.service;

import whut.pojo.CouponInfo;
import whut.pojo.CouponLogs;
import whut.pojo.CouponReceive;
import whut.utils.ResponseData;

public interface ProCouponService {

	ResponseData getList(int pageindex, int pagesize);

	ResponseData addCoupon(CouponInfo couponInfo);

	ResponseData getCouponDetailById(String id);

	ResponseData deleteCoupon(String id);

	ResponseData getCouponByUId(String id,int pageindex, int pagesize);

	ResponseData getCouponLogsList(int pageindex, int pagesize);

	ResponseData getCouponReceiveList(int pageindex, int pagesize);

	ResponseData addCouponLogs(CouponLogs couponLogs);

	ResponseData getCouponLogsDetail(String id, String orderNumber);

	ResponseData getCouponLogsListByStatus(String status,int pageindex, int pagesize);

	ResponseData modifyCouponLogsStatus(String id);

	ResponseData addCouponReceive(CouponReceive couponReceive);

	ResponseData modifyCouponReceiveStatus(String id);

}
