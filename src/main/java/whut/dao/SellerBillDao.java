package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.SellerBill;
import whut.pojo.WithdrawRecord;
import whut.pojo.YieldDetail;

public interface SellerBillDao {

	public void addWithdraw(WithdrawRecord withdrawRecord);

	public List<SellerBill> getList(Map<String, Object> map);

	public void addYield(YieldDetail yieldDetail);

	public List<WithdrawRecord> getWithdrawList(Map<String, Object> map);

	public List<YieldDetail> getYieldList(Map<String, Object> map);

}
