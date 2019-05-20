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
	public ResponseData search(String name,Integer pageindex, Integer pagesize,String field,Byte judge) {
		// TODO Auto-generated method stub
		//pageindex从1开始
		if(pageindex == null) {
			pageindex = 1;
		}
		if(pagesize == null){
			pagesize = 20;
		}
		if(judge == null)
			judge = 0;
		try {
			Jedis jedis = JedisUtil.getJedis();
			String key = "searchKey:"+SysContent.getUserId()+"";
			jedis.lrem(key, 0, name);
			if(jedis.llen(key) < 20) {	//获取set集合长度,从0开始保存20条
				jedis.lpush(key,name);		//如果用户登录了就把用户id和搜索内容存入Redis				
			}else {
				jedis.rpop(key);		//删除最先加入的一个值
				jedis.lpush(key,name);
			}
			JedisUtil.closeJedis(jedis);
			//SolrJUtil.search(pageindex,pagesize,"productName:"+name,new String[] {"productId", "productName","discountRate","price","mainImage"},null,null,null);	//测试
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//如果用户未登录，则获取不到用户ID，SysContent.getUserId()方法会抛出异常，这里不做处理
		}
		String[] queryItem = new String[] {"productId", "productName","discountRate","pscore","mainImage","minPrice","maxPrice","description"};
		return new ResponseData(200,"success",SolrJUtil.searchNew(pageindex,pagesize,"productName:"+name,queryItem,field,judge,null));
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
		String[] queryItem = new String[] {"productId", "productName","discountRate","pscore","mainImage","minPrice","maxPrice","description"};

		//SolrJUtil.search(pageindex,pagesize,"productName:"+name,new String[] {"productId", "productName","discountRate","price","mainImage"},null,null,null);
		return new ResponseData(200,"success",SolrJUtil.search(pageindex,pagesize,searchCondition,queryItem,null,null,null));
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
