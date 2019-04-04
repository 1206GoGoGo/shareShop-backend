package whut.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ProDiscountDao;
import whut.pojo.ProductDiscount;
import whut.service.CommonManageService;
import whut.utils.ResponseData;

@Service
public class CommonManageServiceImpl implements CommonManageService {
	
	@Autowired
	public ProDiscountDao proDiscountDao;
	
	@Override
	public ResponseData getDiscount(int category) {
		if(category != 0) {
			return new ResponseData(4001,"parameter error",null);
		}
		//站点总体折扣在折扣表主键为5，若修改代码也要改--------
		ProductDiscount productDiscount = proDiscountDao.search(String.valueOf(0));
		if(productDiscount==null) {
			return new ResponseData(4002,"exceptions (1) to database storage, please contact the administrator",null);
		}
		if(productDiscount.getDiscountId() != 5) {
			return  new ResponseData(4003,"exceptions (2) to database storage, please contact the administrator",null);
		}
		return new ResponseData(200,"success",productDiscount);
	}

	@Override
	public ResponseData modifyDiscount(ProductDiscount productDiscountNew) {
		int category = productDiscountNew.getCategoryId();
		if(category != 0) {
			return new ResponseData(4001,"parameter error",null);
		}
		//站点总体折扣在折扣表主键为5，若修改代码也要改--------
		ProductDiscount productDiscount = proDiscountDao.search(String.valueOf(0));
		if(productDiscount==null) {
			return new ResponseData(4002,"exceptions (1) to database storage, please contact the administrator",null);
		}
		if(productDiscount.getDiscountId() != 5) {
			return  new ResponseData(4003,"exceptions (2) to database storage, please contact the administrator",null);
		}
		
		//数据库存储无异常
		//处理修改
		productDiscountNew.setDiscountId(5);
		proDiscountDao.modify(productDiscountNew);
		return new ResponseData(200,"success",null);
	}

}
