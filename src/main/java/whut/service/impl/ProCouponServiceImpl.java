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
import whut.utils.SysContent;

@Service
public class ProCouponServiceImpl implements ProCouponService{
	
	@Autowired
	private ProCouponDao proCouponDao;

	@Override
	public ResponseData getList(Integer pageindex, Integer pagesize) {
		// TODO Auto-generated method stub
		if(pageindex == null)
			pageindex = 0;
		if(pagesize == null)
			pagesize = 20;
		Map<String,Object> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<CouponInfo> list = new ArrayList<>();
		list = proCouponDao.getList(map);
		if(list != null) {
			Integer num = proCouponDao.getListNum();
			return new ResponseData(200,"success",list, num);
		}else {
			return new ResponseData(400,"no data",null);
		}
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
	public ResponseData getCouponByUId(String id,Integer pageindex, Integer pagesize) {
		// TODO Auto-generated method stub
		if(pageindex == null)
			pageindex = 0;
		if(pagesize == null)
			pagesize = 20;
		Map<String,Object> map = new HashMap<>();
		map.put("userId", id);
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<CouponReceive> list = new ArrayList<>();
		list = proCouponDao.getCouponByUId(map);
		if(list != null) {
			Integer num = proCouponDao.getCouponByUIdNum(id);
			return new ResponseData(200,"success",list,num);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData addCouponLogs(CouponLogs couponLogs) {
		// TODO Auto-generated method stub
		CouponLogs couponLogs1 = new CouponLogs();
		couponLogs1.setCouponId(couponLogs.getCouponId());
		couponLogs1.setUserId(SysContent.getUserId());
		couponLogs1.setOrderId(couponLogs.getOrderId());
		couponLogs1.setStatus((byte) 1);	//1表示消费成功，0表示消费失败
		proCouponDao.addCouponLogs(couponLogs1);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData getCouponLogsListByStatus(String status,Integer pageindex, Integer pagesize) {
		// TODO Auto-generated method stub
		if(pageindex == null)
			pageindex = 0;
		if(pagesize == null)
			pagesize = 20;
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
	public ResponseData addCouponReceive(CouponReceive couponReceive) {
		// TODO Auto-generated method stub
		CouponReceive couponReceive1 = new CouponReceive();
		couponReceive1.setCouponId(couponReceive.getCouponId());
		couponReceive1.setUserId(SysContent.getUserId());
		couponReceive1.setStatus((byte) 1);		//0表示已使用，1表示未使用
		proCouponDao.addCouponReceive(couponReceive1);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData modifyCouponReceiveStatus(String id) {
		// TODO Auto-generated method stub
		proCouponDao.modifyCouponReceiveStatus(id);
		return new ResponseData(200,"success",null);
	}


	@Override
	public Integer getCouponAmountByUser() {
		return proCouponDao.getCouponAmountByUser(SysContent.getUserId());
	}
	
	
}
