package whut.service;


import org.springframework.web.bind.annotation.RequestBody;

import whut.pojo.OrderDetail;
import whut.pojo.OrderMaster;
import whut.utils.ResponseData;

public interface MemberOrderService {

	public ResponseData getListByUser(int pageindex, int pagesize, int id);

	public ResponseData getListByUserName(int pageindex, int pagesize, String username);

	public ResponseData getListByPro(int pageindex, int pagesize, int id);

	public ResponseData getListByStatus(int pageindex, int pagesize, int status);

	public ResponseData search(String orderNumber);

	public ResponseData getDetailListByOrderId(int orderId);
	
	public ResponseData getDetail(int id);

	public ResponseData modifyOrder(OrderMaster orderMaster);

	public ResponseData modifyPro(OrderDetail orderDetail);

	public ResponseData modifyOrderStatus(@RequestBody String jsonString);

	public ResponseData modifyProStatus(@RequestBody String jsonString);

	public ResponseData getRecordByUser(int pageindex, int pagesize, String user, String timebe, String timeen);

	public ResponseData getCountWeekOrYear(int type);

	public ResponseData getCountWeekOrYearForOnePro(int type, int proId);
}
