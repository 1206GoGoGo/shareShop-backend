package whut.service;


import org.springframework.web.bind.annotation.RequestBody;

import whut.pojo.OrderMaster;
import whut.utils.ResponseData;

public interface MemberOrderService {

	public ResponseData getListByStatus(Integer pageindex, Integer pagesize, Integer status);
	
	public ResponseData getDetail(int id);

	public ResponseData modifyOrder(OrderMaster orderMaster);

	public ResponseData modifyOrderStatus(@RequestBody String jsonString);

	public ResponseData modifyProStatus(@RequestBody String jsonString);

	public ResponseData getRecordByUser(Integer pageindex, Integer pagesize, String timebe, String timeen);

	public ResponseData delete(String jsonString);

	public ResponseData add(String jsonString);

	public ResponseData modifyPay(String jsonString);

}
