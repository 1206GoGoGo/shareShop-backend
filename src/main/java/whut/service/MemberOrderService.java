package whut.service;


import whut.pojo.OrderMaster;
import whut.utils.ResponseData;

public interface MemberOrderService {

	public ResponseData getListByStatus(Integer pageindex, Integer pagesize, Integer status);
	
	public ResponseData getDetail(int id);

	public ResponseData modifyOrder(OrderMaster orderMaster);

	public ResponseData getRecordByUser(Integer pageindex, Integer pagesize, String timebe, String timeen);

	public ResponseData delete(String jsonString);

	public ResponseData add(String jsonString);

	public ResponseData modifyPay(String jsonString);

	public ResponseData receipt(String jsonString);

	public ResponseData applyReturnForOrder(String jsonString);

	public ResponseData applyReturnForDetail(String jsonString);

}
