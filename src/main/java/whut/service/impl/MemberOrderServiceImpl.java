package whut.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import whut.dao.OrderDao;
import whut.dao.OrderReturnDao;
import whut.dao.ProSpecsDao;
import whut.dao.StateTaxDao;
import whut.dao.UserAddrDao;
import whut.dao.UserLoginDao;
import whut.pojo.OrderDetail;
import whut.pojo.OrderMaster;
import whut.pojo.ProductSpecs;
import whut.pojo.SellerBill;
import whut.pojo.UserAddr;
import whut.service.MemberOrderService;
import whut.service.ProDiscountService;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;
import whut.utils.SysContent;

@Service
public class MemberOrderServiceImpl implements MemberOrderService {

	@Autowired
	private OrderDao dao;
	
	@Autowired
	private StateTaxDao stateTaxDao;
	
	@Autowired
	private UserAddrDao userAddrDao;
	
	@Autowired
	private UserLoginDao loginDao;
	
	@Autowired
	private OrderReturnDao orderReturnDao;
	
	@Autowired
	private ProSpecsDao proSpecsDao;
	
	@Autowired
	private ProDiscountService proDiscountService;

	@Override
	public ResponseData getListByStatus(Integer pageindex, Integer pagesize, Integer status) {
		if(pageindex == null) {pageindex = 0;}
		if(pagesize == null) {pagesize = 20;}
		if(status == null) {pageindex = 0;}
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

	/**
	 * 
	 {
		"addrId": 1,
		"coupon":{
			"couponId":1
		},
		"products":[
			{
				"specsId":1,
				"quantity":1
			},
			...
		]
	 }
	 */
	@Override
	public ResponseData add(String jsonString) {
		
		//单品优惠暂不处理！！！！！
		//运费收税？

		BigDecimal productMoney = new BigDecimal("0.0");	//所有商品价格和，不含运费，不含税，不打折（算出来的总和）
		BigDecimal discountMoney = new BigDecimal("0.0");	//打折金额
		BigDecimal shippingMoney = new BigDecimal("0.0");	//运费
		BigDecimal orderMoney = new BigDecimal("0.0");	//订单总金额 已打折 不含运费 不含税
		BigDecimal taxMoney = new BigDecimal("0.0");	//税费
		BigDecimal paymentMoney = new BigDecimal("0.0");	//最终需要支付的金额 orderMoney+taxMoney
		BigDecimal couponMoney = new BigDecimal("0.0");	//优惠券金额
		
		//productMoney = M pro
		//discountMoney = M （productMoney * 打折率）  每个商品求和
		//shippingMoney	默认0
		//orderMoney = productMoney - discountMoney - couponMoney
		//taxMoney = orderMoney * 税率
		//paymentMoney = orderMoney + taxMoney + shippingMoney
		
		
		JsonUtils jsonUtils = new JsonUtils(jsonString);
		JsonNode jsonNode = jsonUtils.getJsonRoot();
		int addrId = jsonUtils.getIntValue("addrId");
		int couponId = jsonNode.get("coupon").findValue("couponId").asInt(0);
		
	    //4.计算优惠券合法性（该用户是否用于，能否正常使用等）
	    couponMoney = new BigDecimal("0");
	    if(couponMoney.doubleValue()<0) {
			return new ResponseData(4001, "优惠券信息异常", null);
	    }
		
		//1.处理收货地址等。初始化订单信息order_master
		UserAddr userAddr = userAddrDao.getAddrByAddrId(addrId);
		if(userAddr == null) {
			return new ResponseData(4002, "地址信息异常", null);
		}
		if(userAddr.getUserId()!=SysContent.getUserId()) {
			return new ResponseData(403, "非法地址信息", null);
		}
		OrderMaster orderMaster = new OrderMaster();
		//生成订单号：订单时间+4位随机数
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		Long orderNumber = Long.parseLong(df.format(new Date())+String.valueOf((Math.random()*9+1)*1000).substring(0, 4));
		orderMaster.setOrderNumber(orderNumber);
		orderMaster.setUserId(SysContent.getUserId());
		orderMaster.setConsigneeName(userAddr.getConsigneeName());
		orderMaster.setConsigneePhone(userAddr.getPhone());
		orderMaster.setPostalCode(userAddr.getPostalCode());
		orderMaster.setState(userAddr.getState());
		orderMaster.setCity(userAddr.getCity());
		orderMaster.setFirstAddr(userAddr.getFirstAddr());
		orderMaster.setSecondAddr(userAddr.getSecondAddr());
		orderMaster.setCreateTime(new Date());
		orderMaster.setOrderStatus((byte) 1);
		
		//int orderId = dao.getOrderIdByOrderNumber(orderNumber);//----------------------------待处理订单id的获取
		//2.处理商品，计算商品总金额及折扣总金额。处理表order_detail
		JsonNode productsNode = jsonNode.get("products");
		int specsId = 0;
		int quantity = 0;
		BigDecimal thisProductMoney = new BigDecimal("0");
		BigDecimal thisDiscountMoney = new BigDecimal("0");
		BigDecimal thisDiscountRate =  new BigDecimal("0");
		
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
	    for (JsonNode objNode : productsNode) {
	    	//处理商品数字中的一个商品
	    	specsId = objNode.findValue("specsId").asInt();
	    	quantity = objNode.findValue("quantity").asInt();
	    	//获取商品信息，填入表中
	    	ProductSpecs productSpecs = proSpecsDao.getProSpecsById(String.valueOf(specsId));
	    	if(productSpecs == null) {
	    		return new ResponseData(4003, productSpecs.getName()+"商品已过期", null);
	    	}
	    	//商品信息插入订单详情表中
	    	OrderDetail orderDetail = new OrderDetail();
	    	//orderDetail.setOrderId(orderId);
	    	orderDetail.setProductId(productSpecs.getProductId());
	    	orderDetail.setProductSpecsId(productSpecs.getProductSpecsId());
	    	orderDetail.setProductName(productSpecs.getName());
	    	orderDetail.setProductQuantity(quantity);
	    	orderDetail.setProductPrice(productSpecs.getPrice());
	    	orderDetail.setStatus((byte) 1);
	    	orderDetailList.add(orderDetail);
	    	//获取折扣率
	    	Integer discountRate = proDiscountService.getDiscountRateById(String.valueOf(specsId));
	    	thisDiscountRate = new BigDecimal(Integer.toString(discountRate)).divide( new BigDecimal("100") );
	    	thisProductMoney = productSpecs.getPrice();
	    	thisDiscountMoney = thisProductMoney.multiply(thisDiscountRate);
	    	productMoney = productMoney.add(thisProductMoney);
	    	discountMoney = discountMoney.add(thisDiscountMoney);
	    }
	    
	    //补充其它需要填充的信息，计算订单中费用、费率、优惠券、优惠等...
	    //3.计算订单金额（打折后不含优惠券）
	    orderMoney = productMoney.subtract(discountMoney);
	    //4.再次计算优惠券(判断条件等信息确定优惠券使用条件)，再计算券后的订单金额
	    couponMoney = new BigDecimal("0");
	    orderMoney = orderMoney.subtract(couponMoney);
	    if(couponMoney.doubleValue()<0) {
			return new ResponseData(4004, "优惠券不满足使用条件", null);
	    }
	    //5.计算运费
	    shippingMoney = new BigDecimal("8");
	    //6.通过收货地址(州地址)，计算税
	    BigDecimal thisTaxRate =  stateTaxDao.getOneStateTaxByName(orderMaster.getState()).divide( new BigDecimal("100") );
	    if(thisTaxRate == null) {
	    	return new ResponseData(4003, "地址信息异常，无法获取税率", null);
	    }
	    taxMoney = orderMoney.multiply(thisTaxRate);
	    //7.计算用户实际需要支付的金额
	    paymentMoney = orderMoney.add(taxMoney).add(shippingMoney);
	    //8.将计算结果填入order_master表中
		//orderMaster.setPaymentMode(paymentMode);
		orderMaster.setOrderMoeny(orderMoney);
		orderMaster.setDiscountMoney(discountMoney);
		orderMaster.setShippingMoney(shippingMoney);
		orderMaster.setPaymentMoney(paymentMoney);
		orderMaster.setTaxMoney(taxMoney);
		//9.提交订单
		dao.addOrderMaster(orderMaster);
		Integer orderId = orderMaster.getOrderId();
		//10.填充订单详情信息，并插入
		for(OrderDetail orderDetail:orderDetailList) {
			orderDetail.setOrderId(orderId);
		}
    	dao.addOrderDetailList(orderDetailList);		
	    return new ResponseData(null);
	}
}
