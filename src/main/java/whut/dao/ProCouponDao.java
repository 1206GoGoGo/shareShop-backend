package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.CouponInfo;
import whut.pojo.CouponLogs;
import whut.pojo.CouponReceive;

public interface ProCouponDao {

	//获取所有优惠券信息
	List<CouponInfo> getList(Map<String, Object> map);

	//根据优惠券ID查看优惠券详情
	CouponInfo getCouponDetailById(String id);

	//根据用户Id查看用户领取的未使用(状态为0)优惠券信息
	List<CouponReceive> getCouponByUId(Map<String, Object> map);

	//向优惠券消费记录表增加记录
	void addCouponLogs(CouponLogs couponLogs);

	//根据状态获取优惠券消费记录列表
	List<CouponLogs> getCouponLogsListByStatus(Map<String, Object> map);

	//向优惠券领取表增加记录
	void addCouponReceive(CouponReceive couponReceive);

	//根据优惠券id修改优惠券领取表状态
	void modifyCouponReceiveStatus(String id);

	//获取所有优惠券信息的数量
	Integer getListNum();

	//根据用户Id查看用户领取的未使用(状态为0)优惠券信息的数量
	Integer getCouponByUIdNum(String id);

	//获取当前登录用户领取优惠券数量
	Integer getCouponAmountByUser(int userId);

	//删除优惠券的张数
	void delCouponRemain(Integer cid);

}
