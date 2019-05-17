package whut.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import whut.dao.ProInfoDao;
import whut.pojo.ProductInfo;
import whut.service.ProInfoService;
import whut.utils.JedisUtil;
import whut.utils.ResponseData;
import whut.utils.SolrJUtil;
import whut.utils.SysContent;



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
		//获取商品表的总数量		
		if(list != null) {
			Integer num = proInfoDao.getListNum();
			return new ResponseData(200,"success",list,num);
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
			//用户点击一个商品详情则记录到Redis浏览信息中（view:商品id）是键，（次数）是值
			Jedis jedis = JedisUtil.getJedis();
			if(jedis.get("view:"+id) == null) {		//获取商品id对应的次数,如果为空说明未存过
				jedis.set("view:"+id, "1");		//第一次存1
			}else {
				jedis.incr("view:"+id);		//对查询到的商品id对应的值，即次数，自增1
			}
			//System.out.println(jedis.get("view:"+id));	//测试输出，根据键（view：商品id）查值（次数）
	    	JedisUtil.closeJedis(jedis);
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
		
		try {
			Jedis jedis = JedisUtil.getJedis();
			jedis.lpush("searchKey:"+SysContent.getUserId()+"", name);	//开启用户登录功能后再添加【增加或覆盖】		
			JedisUtil.closeJedis(jedis);
			//SolrJUtil.search(pageindex,pagesize,"productName:"+name,new String[] {"productId", "productName","discountRate","price","mainImage"},null,null,null);	//测试
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
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
		List<ProductInfo> list = proInfoDao.getListByCategory(map);
		if(list.isEmpty())
			return new ResponseData(400,"No data",null);
		Integer num = proInfoDao.getListByCategoryNum(id);
		return new ResponseData(200,"success",list,num);
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
