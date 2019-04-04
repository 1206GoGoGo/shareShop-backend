package whut.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import whut.pojo.ProductCategory;
import whut.pojo.SellerBill;
import whut.service.MemberOrderService;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;

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
	public ResponseData getListByUser(int pageindex, int pagesize, int id) {
		Map<String, Integer> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		map.put("id", id);
		List<OrderMaster> list = dao.getListByUser(map);
		if(list.isEmpty()) {
			return new ResponseData(400,"no data satify request",null);
		}else {
			return new ResponseData(200,"success",list);
		}
	}
	
	@Override
	public ResponseData getListByUserName(int pageindex, int pagesize, String username) {
		int id = 0;
		try {
			id = loginDao.getLoginInfo(username).getUserId();
		}catch(Exception e) {
			return new ResponseData(4001,"the user does not exist",null);
		}
		
		Map<String, Integer> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		map.put("id", id);
		
		List<OrderMaster> list = dao.getListByUser(map);
		if(list.isEmpty()) {
			return new ResponseData(4002,"the user has no order record",null);
		}else {
			return new ResponseData(200,"success",list);
		}
	}

	@Override
	public ResponseData getListByPro(int pageindex, int pagesize, int id) {
		Map<String, Integer> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		map.put("id", id);
		
		List<OrderDetail> list = dao.getListByPro(map);
		if(list.isEmpty()) {
			return new ResponseData(400,"no data satify request",null);
		}else {
			return new ResponseData(200,"success",list);
		}
	}

	@Override
	public ResponseData getListByStatus(int pageindex, int pagesize, int status) {
		Map<String, Integer> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		map.put("status", status);
		
		List<OrderMaster> list = dao.getListByStatus(map);
		if(list.isEmpty()) {
			return new ResponseData(400,"no data satify request",null);
		}else {
			return new ResponseData(200,"success",list);
		}
	}

	@Override
	public ResponseData search(String orderNumber) {
		OrderMaster orderMaster = dao.searchByOrderNumber(orderNumber);
		List<OrderMaster> list = new ArrayList<OrderMaster>();
		list.add(orderMaster);
		if(orderMaster != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data satify request",null);
		}
	}
	
	@Override
	public ResponseData getDetailListByOrderId(int orderId) {
		List<OrderDetail> list = dao.getDetailListByOrderId(orderId);
		if(list.isEmpty()) {
			return new ResponseData(400,"no data satify request",null);
		}else {
			return new ResponseData(200,"success",list);
		}
	}

	@Override
	public ResponseData getDetail(int orderId) {
		OrderMaster  orderMaster = dao.getMasterByOrderId(orderId);
		if(orderMaster != null) {
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
	 * 通过订单状态判断是否允许修改
	 */
	@Override
	public ResponseData modifyPro(OrderDetail orderDetail) {
		OrderDetail orderDetailOld = dao.getOrderDetailByOrderDetailId(orderDetail.getOrderDetailId());
		
		//判断当前订单状态
		if( orderDetailOld.getStatus()==1 ||  orderDetailOld.getStatus()==2) {
			//满足条件可以修改
		}else {
			return new ResponseData(4061,"current status prohibits modification",null);
		}
		int productIdOld = proSpecsDao.getProSpecsBySpecsId(orderDetailOld.getProductSpecsId()).getProductId();
		int productId = proSpecsDao.getProSpecsBySpecsId(orderDetail.getProductSpecsId()).getProductId();
		if( productIdOld != productId ) {
			return new ResponseData(4062,"no exchange for commodity except for color ..",null);
		}
		
		if( orderDetailOld.getProductPrice() != orderDetail.getProductPrice()) {
			return new ResponseData(4063,"replacement of goods at different prices",null);
		}
		
		//通过修改商品名来修改商品颜色分类，并且是同一个商品，即商品名相同
		orderDetailOld.setProductSpecsId(orderDetail.getProductSpecsId());
		
		dao.modifyPro(orderDetailOld);
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
		
		//发货2—— 4
		if(statusOld == 2 && status == 4) {
			newStatus = 4;
		}
		//同意取消订单11——12
		if(statusOld == 11 && status == 12) {
			newStatus = 12;
		}
		//同意退货申请21——22
		if(statusOld == 21 && status == 22) {
			newStatus = 22;
			returnStatus = 22;
		}
		//收到退货商品，符合退货条件，完成退货22——29
		if(statusOld == 22 && status == 29) {
			newStatus = 29;
			returnStatus = 29;
		}
		//退货商品不符合条件22——23
		if(statusOld == 22 && status == 23) {
			newStatus = 23;
			returnStatus = 23;
		}
		//退货商品重新发货（发还用户）22、23——24
		if( (statusOld == 22 || statusOld ==23) && status == 24) {
			newStatus = 24;
			returnStatus = 24;
		}
		//同意用户换货申请31——32
		if(statusOld == 31 && status == 32) {
			newStatus = 32;
		}
		//商品不符合换货条件，待返回32——33
		if(statusOld == 32 && status == 33) {
			newStatus = 33;
		}
		//换货失败原商品发回33——34
		if(statusOld == 33 && status == 34) {
			newStatus = 34;
		}
		//符合换货标准，待发新商品32——36
		if(statusOld == 32 && status == 36) {
			newStatus = 36;
		}
		//换货商品发出32、36——37
		if( (statusOld == 32 || statusOld == 36) && status == 37) {
			newStatus = 37;
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
		int newStatus = 0;
		int returnStatus = 0;
		//判断当前状态，修改单个状态及整单状态
		//同意退货申请21——22
		if(statusOld == 21 && getStatus == 22) {
			newStatus = 22;
			returnStatus = 22;
		}
		//收到退货商品，符合退货条件，完成退货22——29
		if(statusOld == 22 && getStatus == 29) {
			newStatus = 29;
			returnStatus = 29;
		}
		//退货商品不符合条件22——23
		if(statusOld == 22 && getStatus == 23) {
			newStatus = 23;
			returnStatus = 23;
		}
		//退货商品重新发货（发还用户）22、23——24
		if( (statusOld == 22 || statusOld ==23) && getStatus == 24) {
			newStatus = 24;
			returnStatus = 24;
		}
		//同意用户换货申请31——32
		if(statusOld == 31 && getStatus == 32) {
			newStatus = 32;
		}
		//商品不符合换货条件，待返回32——33
		if(statusOld == 32 && getStatus == 33) {
			newStatus = 33;
		}
		//换货失败原商品发回33——34
		if(statusOld == 33 && getStatus == 34) {
			newStatus = 34;
		}
		//符合换货标准，待发新商品32——36
		if(statusOld == 32 && getStatus == 36) {
			newStatus = 36;
		}
		//换货商品发出32、36——37
		if( (statusOld == 32 || statusOld == 36) && getStatus == 37) {
			newStatus = 37;
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
	public ResponseData getCountWeekOrYear(int type) {

		String list = "[";
		if(type == 1) {
			//一年中每个月的记录
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
			Calendar cal=Calendar.getInstance();
			Date d=cal.getTime();
			for(int i=0;i<13;i++) {
				String day = df.format(d);
				list += "{\"date\":\""+day+"\",\"count\":";
				list += dao.getCountAMonth( cal.get(1)+"-"+cal.get(2)+1 ) + ",\"averageCost\":";
				list += dao.getAverageCostAMonth( cal.get(1)+"-"+cal.get(2)+1 ) + ",\"amount\":";
				list += dao.getAmountAMonth( cal.get(1)+"-"+cal.get(2)+1 );
				if(i<12) {
					list += "},";
				}
		        cal.add(Calendar.MONTH,-1);
		        d=cal.getTime();
			}
		}else if(type == 7) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal=Calendar.getInstance();
			Date d=cal.getTime();
			for(int i=0;i<7;i++) {
				String day = df.format(d);
				list += "{\"date\":\""+day+"\",\"count\":";
				list += dao.getCountADay( day ) + ",\"averageCost\":";
				list += dao.getAverageCostADay( day ) + ",\"amount\":";
				list += dao.getAmountADay( day );
				if(i<6) {
					list += "},";
				}
		        cal.add(Calendar.DATE,-1);
		        d=cal.getTime();
			}
	        
			
		}else {return new ResponseData(406,"parameters incorrect",list);}

		list += "}]";
		//System.out.println(list);	
		return new ResponseData(200,"success",list);
	}

	@Override
	public ResponseData getCountWeekOrYearForOnePro(int type, int proId) {
		Map<String, Object> map = new HashMap<>();
		map.put("proId", proId);
		String list = "[";
		if(type == 1) {
			//一年中每个月的记录
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
			Calendar cal=Calendar.getInstance();
			Date d=cal.getTime();
			for(int i=0;i<13;i++) {
				String day = df.format(d);
				list += "{\"date\":\""+day+"\",\"count\":";
				map.put("date", cal.get(1)+"-"+cal.get(2)+1);
				list += dao.getCountAMonthAPro( map ) + ",\"averageCost\":";
				map.put("date", cal.get(1)+"-"+cal.get(2)+1);
				list += dao.getAverageCostAMonthAPro( map ) + ",\"amount\":";
				map.put("date", cal.get(1)+"-"+cal.get(2)+1);
				list += dao.getAmountAMonthAPro( map );
				if(i<12) {
					list += "},";
				}
		        cal.add(Calendar.MONTH,-1);
		        d=cal.getTime();
			}
		}else if(type == 7) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal=Calendar.getInstance();
			Date d=cal.getTime();
			for(int i=0;i<7;i++) {
				String day = df.format(d);
				list += "{\"date\":\""+day+"\",\"count\":";
				map.put("date", day);
				list += dao.getCountADayAPro( map ) + ",\"averageCost\":";
				map.put("date", day);
				list += dao.getAverageCostADayAPro( map ) + ",\"amount\":";
				map.put("date", day);
				list += dao.getAmountADayAPro( map );
				if(i<6) {
					list += "},";
				}
		        cal.add(Calendar.DATE,-1);
		        d=cal.getTime();
			}
	        
			
		}else {return new ResponseData(406,"parameters incorrect",list);}

		list += "}]";
		//System.out.println(list);	
		return new ResponseData(200,"success",list);
	}

	@Override
	public ResponseData getCountForOneClass(int cateId) {
		ProductCategory productCategory = proCategoryDao.ifCategoryExist(String.valueOf(cateId));
		if(productCategory.equals(null)) {
			return new ResponseData(406,"parameters incorrect",null);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("cateId", cateId);
		map.put("cateLevel", productCategory.getCategoryLevel());
		String list = "[";
		//一年中每个月的记录
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar cal=Calendar.getInstance();
		Date d=cal.getTime();
		for(int i=0;i<13;i++) {
			String day = df.format(d);
			list += "{\"date\":\""+day+"\",\"count\":";
			map.put("day",  cal.get(1)+"-"+cal.get(2)+1 );
			list += dao.getCountAMonthForClass(map) + ",\"averageCost\":";
			list += dao.getAverageCostAMonthForClass(map) + ",\"amount\":";
			list += dao.getAmountAMonthForClass(map);
			if(i<12) {
				list += "},";
			}
	        cal.add(Calendar.MONTH,-1);
	        d=cal.getTime();
		}
		
		return new ResponseData(200,"success",list);
	}

}
