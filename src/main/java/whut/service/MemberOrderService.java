package whut.service;


import org.springframework.web.bind.annotation.RequestBody;

import whut.pojo.OrderDetail;
import whut.pojo.OrderMaster;
import whut.utils.ResponseData;

public interface MemberOrderService {

	public ResponseData getListByStatus(Integer pageindex, Integer pagesize, Integer status);
	
	public ResponseData getDetail(int id);

	public ResponseData modifyOrder(OrderMaster orderMaster);

	public ResponseData modifyOrderStatus(@RequestBody String jsonString);

	public ResponseData modifyProStatus(@RequestBody String jsonString);

	public ResponseData getRecordByUser(int pageindex, int pagesize, String user, String timebe, String timeen);

	public ResponseData delete(int orderId);

	public ResponseData xadd(String jsonString);

}
