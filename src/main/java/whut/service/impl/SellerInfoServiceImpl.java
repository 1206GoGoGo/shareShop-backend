package whut.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.UserInfoDao;
import whut.dao.UserLoginDao;
import whut.pojo.UserInfo;
import whut.service.SellerInfoService;
import whut.utils.ResponseData;

@Service
public class SellerInfoServiceImpl implements SellerInfoService{

	@Autowired
	private UserInfoDao sellerInfoDao;
	
	@Autowired
	private UserLoginDao userLoginDao;
	
	@Override
	public ResponseData getSellerList(int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<UserInfo> list = sellerInfoDao.getSellerList(map);	 
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData addSeller(String id) {
		// TODO Auto-generated method stub
		if(userLoginDao.getLoginInfoById(Integer.parseInt(id)).getLevel().equals(1)
				|| userLoginDao.getLoginInfoById(Integer.parseInt(id)).getLevel().equals(2)) {			 
			userLoginDao.addSeller(id);
			return new ResponseData(200,"success",null);
		}
		return new ResponseData(406,"You are a Seller",null);
	}

	@Override
	public ResponseData deleteSeller(String id) {
		// TODO Auto-generated method stub
		if(userLoginDao.getLoginInfoById(Integer.parseInt(id)).getLevel().equals(3)) {			 
			userLoginDao.deleteSeller(id);
			return new ResponseData(200,"success",null);
		}
		return new ResponseData(406,"You are not a Seller",null);
	}

}
