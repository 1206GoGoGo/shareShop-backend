package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.CouponInfo;
import whut.pojo.CouponLogs;
import whut.pojo.CouponReceive;

public interface ProCouponDao {

	List<CouponInfo> getList(Map<String, Object> map);

	void addCoupon(CouponInfo couponInfo);

	CouponInfo getCouponDetailById(String id);

	void deleteCoupon(String id);

	List<CouponReceive> getCouponByUId(Map<String, Object> map);

	List<CouponLogs> getCouponLogsList(Map<String, Object> map);

	List<CouponReceive> getCouponReceiveList(Map<String, Object> map);

	void addCouponLogs(CouponLogs couponLogs);

	CouponLogs getCouponLogsDetail(Map<String, String> map);

	List<CouponLogs> getCouponLogsListByStatus(Map<String, Object> map);

	void modifyCouponLogsStatus(String id);

	void addCouponReceive(CouponReceive couponReceive);

	void modifyCouponReceiveStatus(String id);

	

}
