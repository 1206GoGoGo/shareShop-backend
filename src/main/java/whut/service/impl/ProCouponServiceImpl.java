package whut.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ProCouponDao;
import whut.pojo.CouponInfo;
import whut.pojo.CouponLogs;
import whut.pojo.CouponReceive;
import whut.service.ProCouponService;
import whut.utils.ResponseData;

@Service
public class ProCouponServiceImpl implements ProCouponService{
	
	@Autowired
	private ProCouponDao proCouponDao;

	@Override
	public ResponseData getList(int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<CouponInfo> list = new ArrayList<>();
		list = proCouponDao.getList(map);
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData addCoupon(CouponInfo couponInfo) {
		// TODO Auto-generated method stub
		proCouponDao.addCoupon(couponInfo);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData getCouponDetailById(String id) {
		// TODO Auto-generated method stub
		CouponInfo couponInfo = proCouponDao.getCouponDetailById(id);
		if(couponInfo != null) {
			return new ResponseData(200,"success",couponInfo);
		}else {
			return new ResponseData(400,"No data",null);
		}
	}

	@Override
	public ResponseData deleteCoupon(String id) {
		// TODO Auto-generated method stub
		proCouponDao.deleteCoupon(id);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData getCouponByUId(String id,int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("userId", id);
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<CouponReceive> list = new ArrayList<>();
		list = proCouponDao.getCouponByUId(map);
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData getCouponLogsList(int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<CouponLogs> list =	new ArrayList<>();
		list = proCouponDao.getCouponLogsList(map);
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData getCouponReceiveList(int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<CouponReceive> list =	new ArrayList<>();
		list = proCouponDao.getCouponReceiveList(map);
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData addCouponLogs(CouponLogs couponLogs) {
		// TODO Auto-generated method stub
		proCouponDao.addCouponLogs(couponLogs);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData getCouponLogsDetail(String id, String orderNumber) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		map.put("couponId", id);
		map.put("orderNumber", orderNumber);
		CouponLogs couponLogs = proCouponDao.getCouponLogsDetail(map);
		if(couponLogs != null) {
			return new ResponseData(200,"success",couponLogs);
		}else {
			return new ResponseData(400,"No data",null);
		}
	}

	@Override
	public ResponseData getCouponLogsListByStatus(String status,int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("status", status);
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<CouponLogs> list =	new ArrayList<>();
		list = proCouponDao.getCouponLogsListByStatus(map);
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData modifyCouponLogsStatus(String id) {
		// TODO Auto-generated method stub
		proCouponDao.modifyCouponLogsStatus(id);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData addCouponReceive(CouponReceive couponReceive) {
		// TODO Auto-generated method stub
		proCouponDao.addCouponReceive(couponReceive);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData modifyCouponReceiveStatus(String id) {
		// TODO Auto-generated method stub
		proCouponDao.modifyCouponReceiveStatus(id);
		return new ResponseData(200,"success",null);
	}
	
	
}
