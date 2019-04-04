package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.OrderDetail;
import whut.pojo.OrderMaster;
import whut.pojo.SellerBill;

public interface OrderDao {

	//String pageindex, String pagesize, String id
	List<OrderMaster> getListByUser(Map<String, Integer> map);

	//String pageindex, String pagesize, int id
	List<OrderDetail> getListByPro(Map<String, Integer> map);

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

	//获取某天的订单数
	int getCountADay(String day);

	//获取某天的订单总额2018-04-12
	String getAmountADay(String day);

	//获取某天出售商品的总成本
	String getAverageCostADay(String day);

	//获取某月的订单数2018-04
	int getCountAMonth(String month);

	//获取某月的订单总额
	String getAmountAMonth(String month);

	//获取某月出售商品的总成本
	String getAverageCostAMonth(String month);

	//获取某商品某月的销量map :proId、date
	int getCountAMonthAPro(Map<String, Object> map);
	
	//获取某商品某月的成本map :proId、date
	String getAverageCostAMonthAPro(Map<String, Object> map);
	
	//获取某商品某月的销售额map :proId、date
	String getAmountAMonthAPro(Map<String, Object> map);
	
	//获取某商品某天的销量map :proId、date
	int getCountADayAPro(Map<String, Object> map);
	
	//获取某商品某天的成本map :proId、date
	String getAverageCostADayAPro(Map<String, Object> map);
	
	//获取某商品某天的销售额map :proId、date
	String getAmountADayAPro(Map<String, Object> map);

	
	//获取某分类下商品的总销量map:cateId\cateLevel分类级别\date2018-08
	int getCountAMonthForClass(Map<String, Object> map);

	//获取某分类下商品的成本map:cateId\cateLevel分类级别\date2018-08
	String getAverageCostAMonthForClass(Map<String, Object> map);

	//获取某分类下商品的销售额map:cateId\cateLevel分类级别\date2018-08
	String getAmountAMonthForClass(Map<String, Object> map);
}
