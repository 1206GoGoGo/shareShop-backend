package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.SellerBill;
import whut.pojo.WithdrawRecord;
import whut.pojo.YieldDetail;

public interface SellerBillDao {

	//添加提现记录
	public void addWithdraw(WithdrawRecord withdrawRecord);

	//获取某一seller的账户余额变动信息
	public List<SellerBill> getList(Map<String, Object> map);

	//添加收益记录
	public void addYield(YieldDetail yieldDetail);

	//获取某一seller所有的提现记录
	public List<WithdrawRecord> getWithdrawList(Map<String, Object> map);

	//获取某一seller所有的收益记录
	public List<YieldDetail> getYieldList(Map<String, Object> map);

	//根据sellerId获取某一seller的账户余额变动信息总数
	public Integer getListNum(String id);

	//获取某一seller所有的收益记录总数
	public Integer getYieldListNum(String id);

	//获取某一seller所有的体现记录总数
	public Integer getWithdrawListNum(String id);

	//插入一个列表
	public void addYieldDetailList(List<YieldDetail> yieldDetailList);

}
