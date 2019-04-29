package whut.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ProInfoDao;
import whut.pojo.ProductInfo;
import whut.service.ProInfoService;
import whut.utils.ResponseData;
import whut.utils.SolrJUtil;



@Service
public class ProInfoServiceImpl implements ProInfoService{

	@Autowired
	private ProInfoDao proInfoDao;
	
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
		List<ProductInfo> list = proInfoDao.getList(map);
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}
	
	@Override
	public ResponseData getListSearch(Integer pageindex, Integer pagesize) {
		// TODO Auto-generated method stub
		if(pageindex == null) {
			pageindex = 1;
		}
		if(pagesize == null){
			pagesize = 20;
		}
		return new ResponseData(200,"success",SolrJUtil.search(pageindex,pagesize,"*:*",new String[] {"productId", "productName","discountRate","pscore","mainImage","minPrice","maxPrice","description"},null,null,null));
	}

	@Override
	public ResponseData getDetail(String id) {
		// TODO Auto-generated method stub
		ProductInfo productInfo = proInfoDao.getDetail(id);
		if(productInfo != null) {
			return new ResponseData(200,"success",productInfo);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	

	@Override
	public ResponseData search(String name,Integer pageindex, Integer pagesize) {
		// TODO Auto-generated method stub
		//pageindex从1开始
		if(pageindex == null) {
			pageindex = 1;
		}
		if(pagesize == null){
			pagesize = 20;
		}
		//SolrJUtil.search(pageindex,pagesize,"productName:"+name,new String[] {"productId", "productName","discountRate","price","mainImage"},null,null,null);
		return new ResponseData(200,"success",SolrJUtil.search(pageindex,pagesize,"productName:"+name,new String[] {"productId", "productName","discountRate","pscore","mainImage","minPrice","maxPrice","description"},null,null,null));
	}

	@Override
	public ResponseData getListByCategory(String id,Integer pageindex, Integer pagesize) {
		// TODO Auto-generated method stub
		if(pageindex == null)
			pageindex = 0;
		if(pagesize == null)
			pagesize = 20;
		Map<String,Object> map = new HashMap<>();
		map.put("oneCategoryId", id);
		map.put("twoCategoryId", id);
		map.put("threeCategoryId", id);
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<ProductInfo> list = new ArrayList<>();
		list = proInfoDao.getListByCategory(map);
		if(list.isEmpty())
			return new ResponseData(400,"No data",null);
		return new ResponseData(200,"success",list);
	}
	
	@Override
	public ResponseData getListByCategorySearch(String id, Integer pageindex, Integer pagesize) {
		// TODO Auto-generated method stub
		if(pageindex == null) {
			pageindex = 1;
		}
		if(pagesize == null){
			pagesize = 20;
		}
		String searchCondition = "oneCategoryId:"+id+" || twoCategoryId:"+id;
		//SolrJUtil.search(pageindex,pagesize,"productName:"+name,new String[] {"productId", "productName","discountRate","price","mainImage"},null,null,null);
		return new ResponseData(200,"success",SolrJUtil.search(pageindex,pagesize,searchCondition,new String[] {"productId", "productName","discountRate","pscore","mainImage","minPrice","maxPrice","description"},null,null,null));
	}

	@Override
	public ResponseData getDetailByCode(String id) {
		// TODO Auto-generated method stub
		ProductInfo productInfo = proInfoDao.getDetailByCode(id);
		if(productInfo != null) {
			return new ResponseData(200,"success",productInfo);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}
	
}
