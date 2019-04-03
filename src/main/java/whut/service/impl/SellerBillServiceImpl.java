package whut.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.SellerBillDao;
import whut.pojo.SellerBill;
import whut.pojo.WithdrawRecord;
import whut.pojo.YieldDetail;
import whut.service.SellerBillService;
import whut.utils.ResponseData;

@Service
public class SellerBillServiceImpl implements SellerBillService{

	@Autowired
	public SellerBillDao sellerBillDao;

	@Override
	public ResponseData getList(String id,int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("userId", id);
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<SellerBill> list = sellerBillDao.getList(map);
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData addWithdraw(WithdrawRecord withdrawRecord) {
		// TODO Auto-generated method stub
		sellerBillDao.addWithdraw(withdrawRecord);
		return new ResponseData(200,"add success",null);
	}

	@Override
	public ResponseData addYield(YieldDetail yieldDetail) {
		// TODO Auto-generated method stub
		sellerBillDao.addYield(yieldDetail);
		return new ResponseData(200,"add success",null);
	}

	@Override
	public ResponseData getWithdrawList(String id,int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("userId", id);
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<WithdrawRecord> list = new ArrayList<>();
		list = sellerBillDao.getWithdrawList(map);
		if(list.isEmpty())
			return new ResponseData(400,"No data",null);
		return new ResponseData(200,"success",list);
	}

	@Override
	public ResponseData getYieldList(String id,int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("userId", id);
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<YieldDetail> list = new ArrayList<>();
		list = sellerBillDao.getYieldList(map);
		if(list.isEmpty())
			return new ResponseData(400,"No data",null);
		return new ResponseData(200,"success",list);
	}
	
}
