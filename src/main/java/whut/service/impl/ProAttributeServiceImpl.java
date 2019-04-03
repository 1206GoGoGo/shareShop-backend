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
	public ResponseData addProductAttributeKey(ProductAttributeKey productAttributeKey) {
		// TODO Auto-generated method stub
		Object productAttributeKey1 = getProductAttributeKeyByIdAndName(productAttributeKey.getCategoryId().toString(),productAttributeKey.getAttrName()).getData();
		if(productAttributeKey1 == null) {
			proAttributeDao.addProductAttributeKey(productAttributeKey);
			return new ResponseData(200,"success",null);
		}
		else
			return new ResponseData(400,"This commodity attribute already exists under this category",null);
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
	public ResponseData modifyProductAttributeKey(ProductAttributeKey productAttributeKey) {
		// TODO Auto-generated method stub
		ProductAttributeKey productAttributeKey1 = new ProductAttributeKey();		
		//只能修改部分参数
		productAttributeKey1.setNameSort(productAttributeKey.getNameSort());
		proAttributeDao.modifyProductAttributeKey(productAttributeKey1);
		return new ResponseData(200,"success",null);
	}


	@Override
	public ResponseData modifyProductAttributeKeyByStatus(String id, String status) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		map.put("keyId", id);
		map.put("status", status);
		proAttributeDao.modifyProductAttributeKeyByStatus(map);
		return new ResponseData(200,"success",null);
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
	public ResponseData addProductAttributeValue(ProductAttributeValue productAttributeValue) {
		// TODO Auto-generated method stub
		Object productAttributeValue1 = getProductAttributeValueByIdAndValue(productAttributeValue.getAttrKeyId().toString(),productAttributeValue.getAttrValue()).getData();
		if(productAttributeValue1 == null) {
			proAttributeDao.addProductAttributeValue(productAttributeValue);
			return new ResponseData(200,"success",null);
		}
		else
			return new ResponseData(400,"This commodity attribute already exists under this category",null);
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

	@Override
	public ResponseData modifyProductAttributeValue(ProductAttributeValue productAttributeValue) {
		// TODO Auto-generated method stub
		ProductAttributeValue productAttributeValue1 = new ProductAttributeValue();
		//修改部分参数
		productAttributeValue1.setAttrValue(productAttributeValue.getAttrValue());
		productAttributeValue1.setValueSort(productAttributeValue.getValueSort());
		proAttributeDao.modifyProductAttributeValue(productAttributeValue1);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData modifyProductAttributeValueByStatus(String id, String status) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		map.put("valueId", id);
		map.put("status", status);
		proAttributeDao.modifyProductAttributeValueByStatus(map);
		return new ResponseData(200,"success",null);
	}
	
}
