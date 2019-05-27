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
import whut.dao.ProInfoDao;
import whut.dao.ProSpecsDao;
import whut.dao.SellerBillDao;
import whut.dao.StateTaxDao;
import whut.dao.UserAddrDao;
import whut.dao.UserInfoDao;
import whut.dao.UserLoginDao;
import whut.pojo.OrderDetail;
import whut.pojo.OrderMaster;
import whut.pojo.ProductSpecs;
import whut.pojo.ReturnRecord;
import whut.pojo.SellerBill;
import whut.pojo.UserAddr;
import whut.pojo.UserInfo;
import whut.pojo.UserLogin;
import whut.pojo.YieldDetail;
import whut.service.MemberOrderService;
import whut.service.ProDiscountService;
import whut.trade.TradeRequest;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;
import whut.utils.SysContent;

@Service
public class MemberOrderServiceImpl implements MemberOrderService {

	@Autowired
	private TradeRequest tradeRequest;
	
	@Autowired
	private OrderDao dao;
	
	@Autowired
	private StateTaxDao stateTaxDao;
	
	@Autowired
	private UserAddrDao userAddrDao;

	@Autowired
	private OrderReturnDao orderReturnDao;
	
	@Autowired
	private ProSpecsDao proSpecsDao;
	
	@Autowired
	private ProInfoDao proInfoDao;
	
	@Autowired
	private ProDiscountService proDiscountService;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private SellerBillDao sellerBillDao;
	
	@Autowired
	private UserLoginDao userLoginDao;

	@Override
	public ResponseData getListByStatus(Integer pageindex, Integer pagesize, Integer status) {
		if(pageindex == null) {pageindex = 0;}
		if(pagesize == null) {pagesize = 20;}
		if(status == null) {status = 0;}
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
		if( orderMasterOld.getOrderStatus()!=1) {
			//不满足条件不可修改
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


	@Override
	public ResponseData getRecordByUser(Integer pageindex, Integer pagesize, String timebe, String timeen) {
		if(pageindex == null) {pageindex = 0;}
		if(pagesize == null) {pagesize = 20;}
		if(timebe == "") {timebe = null;}
		if(timeen == "") {timeen = null;}
		
		int id = SysContent.getUserId();
		
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
	public ResponseData delete(String jsonString) {
		JsonUtils jsonUtils = new JsonUtils(jsonString);
		Integer orderId = jsonUtils.getIntValue("orderId");
		OrderMaster orderMaster = dao.getMasterByOrderId(orderId);
		if(orderMaster==null) {
			return new ResponseData(406,"order does not exist",null);
		}
		if(orderMaster.getUserId() != SysContent.getUserId()) {
			return new ResponseData(403,"illegally accessed",null);
		}
		
		//9,12,13,14,25,29,35,39才允许删除
		int s = orderMaster.getOrderStatus();
		if(s!=9 && s!=12 && s!=13 && s!=14 && s!=25 && s!=29 && s!=35 && s!=39) {
			//禁止删除
			return new ResponseData(4061,"Current status prohibits deletion",null);
		}
		
		Map<String, Integer> map = new HashMap<>();
		map.put("orderId", Integer.valueOf(orderMaster.getState()));
		map.put("status", 19);
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
		BigDecimal orderMoney = new BigDecimal("0.0");	//订单总金额 已打折 不含运费 不含税 已减优惠券
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
		
		//1表示普通用户  2表示会员用户  3表示店主 
		UserLogin userLogin = userLoginDao.getLoginInfoById(SysContent.getUserId());
		int level = userLogin.getLevel();
		//********************************************************************************************

		
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
	    	//根据商品id（不是单品id）获取折扣率
	    	BigDecimal discountRate = BigDecimal.valueOf(0);
	    	if(level == 1) {
	    		discountRate = proInfoDao.getDetail(String.valueOf(productSpecs.getProductId())).getDiscountRate();
	    	}else if(level ==2 || level == 3){
	    		discountRate = proDiscountService.getDiscountRateById(String.valueOf(productSpecs.getProductId()));
	    	}
	    	thisDiscountRate = discountRate.divide( new BigDecimal("100") );
	    	thisProductMoney = productSpecs.getPrice().multiply(BigDecimal.valueOf(quantity));//多个商品的总价格
	    	thisDiscountMoney = thisProductMoney.multiply(thisDiscountRate);
	    	productMoney = productMoney.add(thisProductMoney);
	    	discountMoney = discountMoney.add(thisDiscountMoney);
	    	
	    }
	    
	    //补充其它需要填充的信息，计算订单中费用、费率、优惠券、优惠等...
	    //3.计算订单金额（打折后不含优惠券）
	    orderMoney = productMoney.subtract(discountMoney);
	    //4.再次计算优惠券(判断条件等信息确定优惠券使用条件)，再计算券后的订单金额
	    couponMoney = new BigDecimal("0");
	    if(couponMoney.doubleValue()<0) {
			return new ResponseData(4004, "优惠券不满足使用条件", null);
	    }else {
	    	orderMoney = orderMoney.subtract(couponMoney);
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
		//10.填充订单详情信息，并插入。同时计算单品价格，及收益表信息。
		if(level == 1) {
			//普通用户不用处理收益信息
			for(OrderDetail orderDetail:orderDetailList) {
				orderDetail.setOrderId(orderId);
				//计算商品打折并使用优惠券后实际支付的金额
				//orderDetail.getProductPrice()*orderDetail.getProductQuantity()*thisDiscountRate/(orderMoney+couponMoney) * orderMoney
		    	//根据商品id（不是单品id）获取折扣率
				//BigDecimal discountRate = proDiscountService.getDiscountRateById(String.valueOf(orderDetail.getProductId()));
				BigDecimal discountRate = proInfoDao.getDetail(String.valueOf(orderDetail.getProductId())).getDiscountRate();
		    	thisDiscountRate = BigDecimal.valueOf(1).subtract( discountRate.divide(new BigDecimal("100")) );
		    	//计算
		    	BigDecimal realPay = orderDetail.getProductPrice().multiply(BigDecimal.valueOf(orderDetail.getProductQuantity())).
		    			multiply(thisDiscountRate).multiply(orderMoney).divide(orderMoney.add(couponMoney),2,BigDecimal.ROUND_HALF_UP);
		    	orderDetail.setActualPaidMoney(realPay);
			}
			//添加订单详情
	    	dao.addOrderDetailList(orderDetailList);
		}else {
			int superiorId = 0;
			if(level == 3) {
				superiorId = userLogin.getUserId();
			}else {
				superiorId = userInfoDao.getUserInfo(String.valueOf(SysContent.getUserId())).getSuperiorId();
			}
			for(OrderDetail orderDetail:orderDetailList) {
				orderDetail.setOrderId(orderId);
				
				//计算商品打折并使用优惠券后实际支付的金额
				//orderDetail.getProductPrice()*orderDetail.getProductQuantity()*thisDiscountRate/(orderMoney+couponMoney) * orderMoney
		    	//根据商品id（不是单品id）获取折扣率
				BigDecimal discountRate = proDiscountService.getDiscountRateById(String.valueOf(orderDetail.getProductId()));
		    	thisDiscountRate = BigDecimal.valueOf(1).subtract( discountRate.divide(new BigDecimal("100")) );
		    	//计算
		    	BigDecimal realPay = orderDetail.getProductPrice().multiply(BigDecimal.valueOf(orderDetail.getProductQuantity())).
		    			multiply(thisDiscountRate).multiply(orderMoney).divide(orderMoney.add(couponMoney),2,BigDecimal.ROUND_HALF_UP);
		    	
		    	orderDetail.setActualPaidMoney(realPay);
			}
			//添加订单详情
	    	dao.addOrderDetailList(orderDetailList);    	

	    	
			//计算收益信息
			BigDecimal thisYieldRate =  new BigDecimal("0");
			List<YieldDetail> yieldDetailList = new ArrayList<YieldDetail>();
			orderDetailList = dao.getDetailListByOrderId(orderId);
			for(OrderDetail orderDetail:orderDetailList) {
				
				//计算商品打折并使用优惠券后实际支付的金额
				//orderDetail.getProductPrice()*orderDetail.getProductQuantity()*thisDiscountRate/(orderMoney+couponMoney) * orderMoney
		    	//根据商品id（不是单品id）获取折扣率
				BigDecimal discountRate = proDiscountService.getDiscountRateById(String.valueOf(orderDetail.getProductId()));
		    	thisDiscountRate = BigDecimal.valueOf(1).subtract( discountRate.divide(new BigDecimal("100")) );
		    	//计算
		    	BigDecimal realPay = orderDetail.getProductPrice().multiply(BigDecimal.valueOf(orderDetail.getProductQuantity())).
		    			multiply(thisDiscountRate).multiply(orderMoney).divide(orderMoney.add(couponMoney),2,BigDecimal.ROUND_HALF_UP);
		    	
		    	//向收益表中添加数据
		    	//根据商品id（不是单品id）获取返现率
		    	BigDecimal yieldRate = proDiscountService.getYieldRateById(String.valueOf(orderDetail.getProductId()));
		    	thisYieldRate =  yieldRate.divide( new BigDecimal("100") );
		    	YieldDetail yieldDetail = new YieldDetail();
		    	yieldDetail.setUserId(superiorId);
		    	yieldDetail.setOrderId(orderId);
		    	yieldDetail.setOrderDetailId(orderDetail.getOrderDetailId());
		    	yieldDetail.setActualPaidMoney(realPay);
		    	yieldDetail.setYieldRate(yieldRate);
		    	yieldDetail.setReceivedMoney(realPay.multiply(thisYieldRate));//实际得到的金额
		    	yieldDetail.setPurchaserId(SysContent.getUserId());
		    	yieldDetail.setCreateTime(orderMaster.getCreateTime());
		    	yieldDetail.setStatus((byte) 2);
		    	yieldDetailList.add(yieldDetail);
			}
			sellerBillDao.addYieldDetailList(yieldDetailList);
	    	
		}

	    return new ResponseData(null);
		
	}
	
	@Override
	public ResponseData modifyPay(String jsonString) {
		JsonUtils jsonUtils = new JsonUtils(jsonString);
		Integer orderId = jsonUtils.getIntValue("orderId");
		OrderMaster orderMaster = dao.getMasterByOrderId(orderId);
		if(orderMaster==null) {
			return new ResponseData(406,"order does not exist",null);
		}
		if(orderMaster.getUserId() != SysContent.getUserId()) {
			return new ResponseData(403,"illegally accessed",null);
		}
		
		//1才允许支付
		int s = orderMaster.getOrderStatus();
		if(s!=1) {
			return new ResponseData(4061,"Current status prohibits pay",null);
		}
		tradeRequest.tradePay(String.valueOf(orderMaster.getOrderNumber()), "phone", "订单支付", orderMaster.getPaymentMoney(),
				(byte) 1, orderMaster.getOrderId(), "用户支付", "pay way card", String.valueOf(orderMaster.getUserId()), "60*60*2");
		Map<String, Integer> map = new HashMap<>();
		map.put("orderId", orderId);
		map.put("status", 2);
		dao.modifyOrderStatus(map);
		dao.modifyProStatusByOrderId(map);
		return new ResponseData(null);
	}

	@Override
	public ResponseData receipt(String jsonString) {
		JsonUtils jsonUtils = new JsonUtils(jsonString);
		int orderId = jsonUtils.getIntValue("orderId");
		OrderMaster orderMaster = dao.getMasterByOrderId(orderId);
		
		if(orderMaster == null) {
			return new ResponseData(4061,"order does not exist",null);
		}

		if(orderMaster.getUserId() != SysContent.getUserId()) {
			return new ResponseData(403,"illegally accessed",null);
		}
		
		if(orderMaster.getOrderStatus()!=4) {
			return new ResponseData(4062,"未发货无法确认收货",null);
		}
		
		//修改订单状态和子订单状态
		Map<String, Integer> map = new HashMap<>();
		map.put("orderId", orderId);
		map.put("status", 5);
		dao.modifyOrderStatus(map);

		Map<String, Integer> mapBill = new HashMap<>();
		mapBill.put("orderId", orderId);
		mapBill.put("status", 1);

		//将该订单下正常订单（没有退货请求的）进行处理，由发货状态4改为确认收货5
		List<OrderDetail> proList = dao.getDetailListByOrderId(orderId);
		for(OrderDetail tempOrderDetail : proList) {
			if(tempOrderDetail.getStatus() == 4) {
				dao.modifyProStatus(map);
				//处理收益表
				sellerBillDao.modifyStatusByOrderDetailId(mapBill);
			}
		}
		
		return new ResponseData(200,"success",null);
	
	}

	@Override
	public ResponseData applyReturnForOrder(String jsonString) {
		JsonUtils jsonUtils = new JsonUtils(jsonString);
		int orderId = jsonUtils.getIntValue("orderId");
		OrderMaster orderMaster = dao.getMasterByOrderId(orderId);
		
		if(orderMaster == null) {
			return new ResponseData(4061,"order does not exist",null);
		}

		if(orderMaster.getUserId() != SysContent.getUserId()) {
			return new ResponseData(403,"illegally accessed",null);
		}
		
		//判断订单状态和子订单状态是否都一致。
		int statusDetail = orderMaster.getOrderStatus();
		List<OrderDetail> proList = dao.getDetailListByOrderId(orderId);
		for(OrderDetail tempOrderDetail : proList) {
			if(tempOrderDetail.getStatus() != statusDetail) {
				return new ResponseData(4062,"订单状态不一致，禁止整单操作",null);
			}
		}
		

		Map<String, Integer> map = new HashMap<>();
		map.put("orderId", orderId);
		//未付款1->13
		if(statusDetail == 1) {
			map.put("status", 13);
			dao.modifyOrderStatus(map);
			dao.modifyProStatusByOrderId(map);
			//修改收益表
			map.put("status", -1);
			sellerBillDao.modifyStatusByOrderId(map);
		}
		
		//付款未发货2->11
		if(statusDetail == 2) {
			map.put("status", 11);
			dao.modifyOrderStatus(map);
			dao.modifyProStatusByOrderId(map);
			ReturnRecord returnRecord = new ReturnRecord();
			returnRecord.setUserId(orderMaster.getUserId());
			returnRecord.setOrderId(orderMaster.getOrderId());
			returnRecord.setOrderDetailId(0);
			returnRecord.setProductSpecsId(0);
			returnRecord.setReturnType((byte) 1);
			returnRecord.setReturnMoney(orderMaster.getPaymentMoney());
			returnRecord.setCreateTime(new Date());
			returnRecord.setStatus((byte) 11);
			returnRecord.setReason("未发货申请退款");
			orderReturnDao.addReturn(returnRecord);
		}
		//已发货4->21
		if(statusDetail == 4 || statusDetail == 5) {
			map.put("status", 21);
			dao.modifyOrderStatus(map);
			dao.modifyProStatusByOrderId(map);
			ReturnRecord returnRecord = new ReturnRecord();
			returnRecord.setUserId(orderMaster.getUserId());
			returnRecord.setOrderId(orderMaster.getOrderId());
			returnRecord.setOrderDetailId(0);
			returnRecord.setProductSpecsId(0);
			returnRecord.setReturnType((byte) 1);
			returnRecord.setReturnMoney(orderMaster.getPaymentMoney());
			returnRecord.setCreateTime(new Date());
			returnRecord.setStatus((byte) 21);
			returnRecord.setReason("未发货申请退款");
			orderReturnDao.addReturn(returnRecord);
		}

		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData applyReturnForDetail(String jsonString) {
		JsonUtils jsonUtils = new JsonUtils(jsonString);
		int orderDetailId = jsonUtils.getIntValue("orderDetailId");
		OrderDetail orderDetail = dao.getOrderDetailByOrderDetailId(orderDetailId);
		

		Map<String, Integer> map = new HashMap<>();
		map.put("orderDetailId", orderDetailId);
		//付款未发货2->11
		if(orderDetail.getStatus() == 2) {
			map.put("status", 11);
			dao.modifyProStatus(map);
			ReturnRecord returnRecord = new ReturnRecord();
			returnRecord.setUserId(SysContent.getUserId());
			returnRecord.setOrderId(orderDetail.getOrderId());
			returnRecord.setOrderDetailId(orderDetailId);
			returnRecord.setProductSpecsId(orderDetail.getProductSpecsId());
			returnRecord.setReturnType((byte) 0);
			returnRecord.setReturnMoney(orderDetail.getActualPaidMoney());
			returnRecord.setCreateTime(new Date());
			returnRecord.setStatus((byte) 11);
			returnRecord.setReason("未发货申请退款");
			orderReturnDao.addReturn(returnRecord);
		}
		//已发货4->21
		if(orderDetail.getStatus() == 4 || orderDetail.getStatus() == 5) {
			map.put("status", 21);
			dao.modifyProStatus(map);
			ReturnRecord returnRecord = new ReturnRecord();
			returnRecord.setUserId(SysContent.getUserId());
			returnRecord.setOrderId(orderDetail.getOrderId());
			returnRecord.setOrderDetailId(orderDetailId);
			returnRecord.setProductSpecsId(orderDetail.getProductSpecsId());
			returnRecord.setReturnType((byte) 0);
			returnRecord.setReturnMoney(orderDetail.getActualPaidMoney());
			returnRecord.setCreateTime(new Date());
			returnRecord.setStatus((byte) 21);
			returnRecord.setReason("未发货申请退款");
			orderReturnDao.addReturn(returnRecord);
		}

		return new ResponseData(200,"success",null);

	}
}
