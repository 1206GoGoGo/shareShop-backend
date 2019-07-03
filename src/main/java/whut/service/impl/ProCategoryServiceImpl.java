package whut.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import whut.dao.ProCategoryDao;
import whut.pojo.ProductCategory;
import whut.service.ProCategoryService;
import whut.utils.JedisUtil;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;


@Service
public class ProCategoryServiceImpl implements ProCategoryService{

	@Autowired
	private ProCategoryDao proCategoryDao;
	
	@Override
	public ResponseData getList() {
		
		//查询缓存
		try {
			Jedis jedis = JedisUtil.getJedis();
			String jsonstr = jedis.get("proCategoryInfo");
			JedisUtil.closeJedis(jedis);
			//如果存在，说明有缓存
			if(jsonstr !=null) {
				List<ProductCategory> list = JsonUtils.jsonToList(jsonstr, ProductCategory.class);
				return new ResponseData(200,"success",list);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		List<ProductCategory> list = proCategoryDao.getList();
		if(list != null) {
			//向缓存中添加数据
			try {
				Jedis jedis = JedisUtil.getJedis();
				jedis.set("proCategoryInfo", JsonUtils.objectToJson(list));
				JedisUtil.closeJedis(jedis);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ProductCategory ifCategoryExist(String categoryCode) {
		return proCategoryDao.ifCategoryExist(categoryCode);
	}

	@Override
	public List<ProductCategory> ifHaveChild(String id) {
		return proCategoryDao.ifHaveChild(id);
	}


	@Override
	public ResponseData getListByParentId(String id) {
		
		// 查询缓存
		try {
			Jedis jedis = JedisUtil.getJedis();
			String jsonstr = jedis.hget("proCategoryContent",id+"");
			JedisUtil.closeJedis(jedis);
			// 如果存在，说明有缓存
			if (jsonstr != null) {
				System.out.println("这里有缓存！！！！");
				List<ProductCategory> list = JsonUtils.jsonToList(jsonstr, ProductCategory.class);
				return new ResponseData(200, "success", list);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		List<ProductCategory> list = new ArrayList<>();
		list = proCategoryDao.ifHaveChild(id);
		if(list.size() == 0) {
			return new ResponseData(406,"There are no subcategories",null);
		}
		//向缓存中添加数据
		try {
			Jedis jedis = JedisUtil.getJedis();
			jedis.hset("proCategoryContent",id+"", JsonUtils.objectToJson(list));
			JedisUtil.closeJedis(jedis);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseData(200,"success",list);
	}

}
