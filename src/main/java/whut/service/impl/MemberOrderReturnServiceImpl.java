package whut.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.OrderDao;
import whut.dao.OrderReturnDao;
import whut.pojo.OrderDetail;
import whut.pojo.ReturnRecord;
import whut.service.MemberOrderReturnService;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;
import whut.utils.SysContent;

@Service
public class MemberOrderReturnServiceImpl implements MemberOrderReturnService {

	@Autowired
	private OrderReturnDao orderReturnDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public ResponseData getListByUser(int pageindex, int pagesize) {
		Map<String, Integer> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		map.put("userId", SysContent.getUserId());
		List<ReturnRecord> list = orderReturnDao.getListByUser(map);
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
		List<ReturnRecord> list = orderReturnDao.getListByStatus(map);
		if(list.isEmpty()) {
			return new ResponseData(400,"no data satify request",null);
		}else {
			return new ResponseData(200,"success",list);
		}
	}

	@Override
	public ResponseData getListByOrderId(int orderId) {
		List<ReturnRecord> list = orderReturnDao.getListByOrderId(orderId);
		if(list.isEmpty()) {
			return new ResponseData(400,"no data satify request",null);
		}else {
			return new ResponseData(200,"success",list);
		}
	}

	@Override
	public ResponseData getListByReturnType(int pageindex, int pagesize, int type) {
		Map<String, Integer> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		map.put("type", type);
		List<ReturnRecord> list = orderReturnDao.getListByReturnType(map);
		if(list.isEmpty()) {
			return new ResponseData(400,"no data satify request",null);
		}else {
			return new ResponseData(200,"success",list);
		}
	}

	@Override
	public ResponseData addReturn(String jsonString) {
		JsonUtils jsonUtils = new JsonUtils(jsonString);
		int orderDetailId = jsonUtils.getIntValue("orderDetailId");
		double returnMoney = jsonUtils.getDoubleValue("returnMoney");
		String reason = jsonUtils.getStringValue("reason");
		
		OrderDetail orderDetail = orderDao.getOrderDetailByOrderDetailId(orderDetailId);
		if(orderDetail == null) {
			return new ResponseData(400,"no data satify request",null);
		}
		if(orderDao.getMasterByOrderId(orderDetail.getOrderId()).getUserId() != SysContent.getUserId()) {
			return new ResponseData(403,"illegally accessed",null);
		}
		
		ReturnRecord returnRecord = new ReturnRecord();
		returnRecord.setUserId(SysContent.getUserId());
		returnRecord.setOrderId(orderDetail.getOrderId());
		returnRecord.setOrderDetailId(orderDetailId);
		returnRecord.setProductSpecsId(orderDetail.getProductSpecsId());
		returnRecord.setReturnType((byte) 2);
		returnRecord.setReturnMoney(BigDecimal.valueOf(returnMoney));
		returnRecord.setCreateTime(new Date());
		returnRecord.setStatus((byte) 21);
		returnRecord.setReason(reason);
		
		orderReturnDao.addReturn(returnRecord);
		
		return new ResponseData("success");
	}
}