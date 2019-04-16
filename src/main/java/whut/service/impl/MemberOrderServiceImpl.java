package whut.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.OrderDao;
import whut.dao.OrderReturnDao;

import whut.dao.ProCategoryDao;
import whut.dao.ProSpecsDao;
import whut.dao.UserLoginDao;
import whut.pojo.OrderDetail;
import whut.pojo.OrderMaster;
import whut.pojo.SellerBill;
import whut.service.MemberOrderService;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;
import whut.utils.SysContent;

@Service
public class MemberOrderServiceImpl implements MemberOrderService {

	@Autowired
	private OrderDao dao;
	
	@Autowired
	private ProSpecsDao proSpecsDao;
	
	@Autowired
	private UserLoginDao loginDao;
	
	@Autowired
	private OrderReturnDao orderReturnDao;
	
	@Autowired
	private ProCategoryDao proCategoryDao;

	@Override
	public ResponseData getListByStatus(int pageindex, int pagesize, int status) {
		Map<String, Integer> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		map.put("status", status);
		map.put("userId", SysContent.getUserId());
		
		List<OrderMaster> list = dao.getListByStatus(map);
		if(list.isEmpty()) {
			return new ResponseData(400,"no data satify request",null);
		}else {
			return new ResponseData(200,"success",list);
		}
	}

	@Override
	public ResponseData getDetail(int orderId) {
		OrderMaster orderMaster = dao.getMasterByOrderId(orderId);
		if(orderMaster != null) {
			if(orderMaster.getUserId() != SysContent.getUserId()) {
				return new ResponseData(403,"illegally accessed",null);
			}
			return new ResponseData(200,"success",orderMaster);
		}else {
			return new ResponseData(400,"no data satify request",null);
		}
	}

	/**
	 * 修改订单信息
	 * 需要判断订单状态（订单已关闭等状态、禁止修改）
	 * 订单已发货（就不能修改物流信息<地址单号等>）
	 * 订单完成等状态也不能修改了
	 */
	@Override
	public ResponseData modifyOrder(OrderMaster orderMaster) {
		OrderMaster orderMasterOld = dao.getMasterByOrderId(orderMaster.getOrderId());
		if(orderMasterOld.getUserId() != SysContent.getUserId()) {
			return new ResponseData(403,"illegally accessed",null);
		}
		//判断当前订单状态
		if( orderMasterOld.getOrderStatus()==1 ||  orderMasterOld.getOrderStatus()==2) {
			//满足条件可以修改
		}else {
			return new ResponseData(4061,"current status prohibits modification",null);
		}

		
		//只修改部分允许修改的数据
		orderMasterOld.setConsigneeName(orderMaster.getConsigneeName());
		orderMasterOld.setConsigneePhone(orderMaster.getConsigneePhone());
		orderMasterOld.setPostalCode(orderMaster.getPostalCode());
		orderMasterOld.setState(orderMaster.getState());
		orderMasterOld.setCity(orderMaster.getCity());
		orderMasterOld.setFirstAddr(orderMaster.getFirstAddr());
		orderMasterOld.setSecondAddr(orderMaster.getSecondAddr());
		orderMasterOld.setExpressNumber(orderMaster.getExpressNumber());
		
		dao.modifyOrder(orderMasterOld);
		return new ResponseData(200,"success",null);
	
	}

	/**
	 * 单向：不能从异常修改回来
	 * 待处理、发货、确认收货
	 */
	@Override
	public ResponseData modifyOrderStatus(String jsonString) {
		JsonUtils jsonUtils = new JsonUtils(jsonString);
		String orderId = jsonUtils.getStringValue("orderId");
		int status = jsonUtils.getIntValue("status");
		
		OrderMaster orderMaster = dao.getMasterByOrderId(Integer.parseInt(orderId));
		

		if(orderMaster.getUserId() != SysContent.getUserId()) {
			return new ResponseData(403,"illegally accessed",null);
		}
		
		int statusOld;
		try {
			statusOld = orderMaster.getOrderStatus();
		}catch(Exception e) {
			return new ResponseData(406,"order does not exist",null);
		}
		
		//若订单下商品状态不同则禁止修改
		List<OrderDetail> proList = dao.getDetailListByOrderId(Integer.parseInt(orderId));
		
		int tempStatus = proList.get(0).getStatus();
		for( int i = 1 ; i < proList.size() ; i++) {
			//获取详情状态
			if(proList.get(i).getStatus() != tempStatus) {
				return new ResponseData(4061,"当前订单中子商品状态不一致禁止整单操作",null);
			}
		}
		
		if(tempStatus != statusOld) {
			return new ResponseData(4062,"订单状态和订单下商品状态不一致禁止整单修改",null);	
		}
		
		
		int newStatus = 0;
		int returnStatus = 0;
		
		
		//未确认收货，申请退货4——21
		if(statusOld == 4 && status == 21) {
			newStatus = 21;
			returnStatus = 21;
		}
		//确认收货后申请退货5——21
		if(statusOld == 5 && status == 21) {
			newStatus = 21;
			returnStatus = 21;
		}

		if(newStatus==0) {
			return new ResponseData(4063,"当前订单状态禁止修改或无法从原有状态修改到指定状态",null);
		}

		
		//修改订单状态和子订单状态
		Map<String, String> map = new HashMap<>();
		map.put("orderId", orderId);
		map.put("status", String.valueOf(status));
		dao.modifyOrderStatus(map);
		dao.modifyProStatusByOrderId(map);
		
		//处理退货，同时处理退货表数据
		if(returnStatus!=0) {
			orderReturnDao.modifyStatusByOrderId(map);
		}
		
		//修改全部子账单状态
		return new ResponseData(200,"success",null);
	
	}

	/**
	 * 判断条件
	 */
	@Override
	public ResponseData modifyProStatus(String jsonString) {
		JsonUtils jsonUtils = new JsonUtils(jsonString);
		String orderDetailId = jsonUtils.getStringValue("orderDetailId");
		String status = jsonUtils.getStringValue("status");
		
		OrderDetail orderDetailOld = dao.getOrderDetailByOrderDetailId(Integer.parseInt(orderDetailId));

		
		int getStatus = Integer.parseInt(status);	//	传入的新status
		int statusOld;
		try {
			statusOld = orderDetailOld.getStatus();
		}catch(Exception e) {
			return new ResponseData(406,"order does not exist",null);
		}

		if(dao.getMasterByOrderId(orderDetailOld.getOrderId()).getUserId() != SysContent.getUserId()) {
			return new ResponseData(403,"illegally accessed",null);
		}
		
		
		int newStatus = 0;
		int returnStatus = 0;
		//判断当前状态，修改单个状态及整单状态

		//未确认收货，申请退货4——21
		if(statusOld == 4 && statusOld == 21) {
			newStatus = 21;
			returnStatus = 21;
		}
		//确认收货后申请退货5——21
		if(statusOld == 5 && statusOld == 21) {
			newStatus = 21;
			returnStatus = 21;
		}
		
		
		if(newStatus==0) {
			return new ResponseData(4061,"当前订单状态禁止修改或无法从原有状态修改到指定状态",null);
		}
		
		//修改单个商品状态
		Map<String, String> map = new HashMap<>();
		map.put("orderDetailId", orderDetailId);
		map.put("status", status);
		dao.modifyProStatus(map);
		
		//修改退货相关信息
		if(returnStatus!=0) {
			orderReturnDao.modifyStatusByOrderDetailId(map);
		}
		
		//修改整个订单状态
		Map<String, String> mapOrder = new HashMap<>();
		mapOrder.put("orderId", String.valueOf(orderDetailOld.getOrderId()) );
		mapOrder.put("status", status);
		dao.modifyOrderStatus(mapOrder);

		return new ResponseData(200,"success",null);
	
	}

	@Override
	public ResponseData getRecordByUser(int pageindex, int pagesize, String user, String timebe, String timeen) {
		int id = 0;
		try {
			id = loginDao.getLoginInfo(user).getUserId();
		}catch(Exception e) {
			return new ResponseData(4001,"the user does not exist",null);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		map.put("id", id);
		map.put("timebe", timebe);
		map.put("timeen", timeen);
		
		List<SellerBill> list = dao.getRecordByUser(map);
		if(list.isEmpty()) {
			return new ResponseData(4002,"the user has no order record",null);
		}else {
			return new ResponseData(200,"success",list);
		}
	}

	@Override
	public ResponseData delete(int orderId) {
		OrderMaster orderMaster = dao.getMasterByOrderId(orderId);
		if(orderMaster==null) {
			return new ResponseData(406,"order does not exist",null);
		}
		if(orderMaster.getUserId() != SysContent.getUserId()) {
			return new ResponseData(403,"illegally accessed",null);
		}
		
		//9,12,13,14,25,29,35,39才允许删除
		int s = Integer.parseInt(orderMaster.getState());
		if(s!=9 && s!=12 && s!=13 && s!=14 && s!=25 && s!=29 && s!=35 && s!=39) {
			//禁止删除
			return new ResponseData(4061,"Current status prohibits deletion",null);
		}
		
		Map<String, String> map = new HashMap<>();
		map.put("orderId", orderMaster.getState());
		map.put("status", "19");
		dao.modifyOrderStatus(map);
		
		return new ResponseData(null);
	}
}
