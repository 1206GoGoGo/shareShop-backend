package whut.service;

import whut.pojo.CouponLogs;
import whut.pojo.CouponReceive;
import whut.utils.ResponseData;

public interface ProCouponService {

	ResponseData getList(int pageindex, int pagesize);

	ResponseData getCouponDetailById(String id);

	ResponseData getCouponByUId(String id, int pageindex, int pagesize);

	ResponseData addCouponLogs(CouponLogs couponLogs);

	ResponseData getCouponLogsListByStatus(String status, int pageindex, int pagesize);

	ResponseData addCouponReceive(CouponReceive couponReceive);

	ResponseData modifyCouponReceiveStatus(String id);

}
