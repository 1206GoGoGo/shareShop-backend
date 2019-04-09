package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.OrderDetail;
import whut.pojo.OrderMaster;
import whut.pojo.SellerBill;

public interface OrderDao {

	//String pageindex, String pagesize, String id
	List<OrderMaster> getListByUser(Map<String, Integer> map);

	//String pageindex, String pagesize, String status
	List<OrderMaster> getListByStatus(Map<String, Integer> map);

	OrderMaster searchByOrderNumber(String orderNumber);
	
	OrderMaster getMasterByOrderId(int orderId);

	List<OrderDetail> getDetailListByOrderId(int orderId);

	void modifyOrder(OrderMaster orderMaster);

	void modifyPro(OrderDetail orderDetail);

	//int orderId, Byte status
	void modifyOrderStatus(Map<String, String> map);

	//int orderDetailId, Byte status
	void modifyProStatus(Map<String, String> map);

	OrderDetail getOrderDetailByOrderDetailId(Integer orderDetailId);

	//通过订单号修改订单下所有商品对应的状态
	//int orderId, Byte status
	void modifyProStatusByOrderId(Map<String, String> map);

	//int pageindex, int pagesize, String user, String timebe, String timeen
	List<SellerBill> getRecordByUser(Map<String, Object> map);
}
