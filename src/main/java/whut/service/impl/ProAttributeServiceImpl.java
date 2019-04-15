package whut.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ProAttributeDao;
import whut.pojo.ProductAttributeKey;
import whut.pojo.ProductAttributeValue;
import whut.service.ProAttributeService;
import whut.utils.ResponseData;

@Service
public class ProAttributeServiceImpl implements ProAttributeService{
	
	@Autowired
	private ProAttributeDao proAttributeDao;

	@Override
	public ResponseData getProductAttributeKeyByCategoryID(String id) {
		// TODO Auto-generated method stub
		List<ProductAttributeKey> list = proAttributeDao.getProductAttributeKeyByCategoryID(id);
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData getProductAttributeValueByKeyID(String id) {
		// TODO Auto-generated method stub
		List<ProductAttributeValue> list = proAttributeDao.getProductAttributeValueByKeyID(id);
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

}
