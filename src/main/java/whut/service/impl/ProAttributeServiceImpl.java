package whut.service.impl;

import java.util.ArrayList;
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
	public ResponseData getProductAttributeKeyList(int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		Map<String,Integer> map = new HashMap<>();
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<ProductAttributeKey> list = new ArrayList<>();
		list = proAttributeDao.getProductAttributeKeyList(map);
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData getProductAttributeKeyByIdAndName(String id, String name) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		map.put("categoryId", id);
		map.put("attrName", name);
		ProductAttributeKey productAttributeKey = proAttributeDao.getProductAttributeKeyByIdAndName(map);
		if(productAttributeKey != null)
			return new ResponseData(200,"success",productAttributeKey);
		else
			return new ResponseData(400,"no data",null);
	}


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

	@Override
	public ResponseData getProductAttributeValueByIdAndValue(String id, String value) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		map.put("attrKeyId", id);
		map.put("attrValue", value);
		ProductAttributeValue productAttributeValue = proAttributeDao.getProductAttributeValueByIdAndValue(map);
		if(productAttributeValue != null)
			return new ResponseData(200,"success",productAttributeValue);
		else
			return new ResponseData(400,"no data",null);
	}

}
