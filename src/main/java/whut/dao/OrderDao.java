package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.OrderDetail;
import whut.pojo.OrderMaster;
import whut.pojo.SellerBill;

public interface OrderDao {

	//String pageindex, String pagesize, int status=0查全部, int userId
	List<OrderMaster> getListByStatus(Map<String, Integer> map);
	
	OrderMaster getMasterByOrderId(int orderId);

	List<OrderDetail> getDetailListByOrderId(int orderId);

	void modifyOrder(OrderMaster orderMaster);

	//void modifyPro(OrderDetail orderDetail);

	//int orderId, Byte status
	void modifyOrderStatus(Map<String, Integer> map);

	//通过订单id修改订单下所有商品对应的状态
	//int orderId, Byte status
	void modifyProStatusByOrderId(Map<String, Integer> map);
	
	//int orderDetailId, Byte status
	void modifyProStatus(Map<String, Integer> map);

	OrderDetail getOrderDetailByOrderDetailId(Integer orderDetailId);


	//int pageindex, int pagesize, int id（用户id）, String timebe, String timeen(20180101格式)   time=null查询所有数据
	List<SellerBill> getRecordByUser(Map<String, Object> map);

	//添加新订单
	void addOrderMaster(OrderMaster orderMaster);

	//添加订单详情
	void addOrderDetailList(List<OrderDetail> orderDetailList);

}
