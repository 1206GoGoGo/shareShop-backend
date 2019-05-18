package whut.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ProDiscountDao;
import whut.dao.ProInfoDao;
import whut.dao.ProSpecsDao;
import whut.pojo.ProductDiscount;

import whut.service.ProDiscountService;
import whut.utils.ResponseData;


@Service
public class ProDiscountServiceImpl implements ProDiscountService{

	@Autowired
	private ProDiscountDao proDiscountDao;
	
	@Autowired
	private ProSpecsDao proSpecsDao;
	
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
		List<ProductDiscount> list = proDiscountDao.getList(map);
		if(list != null) {
			Integer num = proDiscountDao.getListNum();
			return new ResponseData(200,"success",list,num);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData search(String id) {
		// TODO Auto-generated method stub
		ProductDiscount productDiscount = new ProductDiscount();
		productDiscount = proDiscountDao.search(id);
		if(productDiscount == null)
			return new ResponseData(400,"not find",null);
		return new ResponseData(200,"success",productDiscount);
	}

	@Override
	public ResponseData add(ProductDiscount productDiscount) {
		// TODO Auto-generated method stub
		if(proDiscountDao.search(productDiscount.getCategoryId().toString()) == null) {
			proDiscountDao.add(productDiscount);
			return new ResponseData(200,"Successfully added",null);
		}
		return new ResponseData(500,"Add failed",null);
	}

	@Override
	public ResponseData modify(ProductDiscount productDiscount) {
		// TODO Auto-generated method stub
		proDiscountDao.modify(productDiscount);
		return new ResponseData(200,"modify success",null);
	}

	
	//根据商品id查询商品的折扣率并返回，先查询商品信息表--再到商品折扣表，折扣表中如果分类id为0则表示全场打折
	@Override
	public Integer getDiscountRateById(String id) {
		// TODO Auto-generated method stub
		//定义返回的折扣率
		Integer result = null;
		//Integer proId = proSpecsDao.getProSpecsById(id).getProductId();	//通过商品规格id得到商品id
		Integer proId = Integer.parseInt(id);	//直接传递商品ID进来
		if(proInfoDao.getDetail(proId.toString()).getDiscountRate() == null || proInfoDao.getDetail(proId.toString()).getDiscountRate() == 0) {//通过商品id得到商品折扣率，先判断商品是否打折，如果折扣率为空即无折扣
			//如果未设置商品折扣则去折扣表查看，要知道折扣得先知道商品所属分类
			//先判断商品所属的二级分类是否打折
			if(proInfoDao.getDetail(proId.toString()).getTwoCategoryId() != null) {		//如果商品属于二级分类下
				Integer proCategoryId = proInfoDao.getDetail(proId.toString()).getTwoCategoryId();
				if(proDiscountDao.search(proCategoryId.toString()) != null) {	//根据二级分类查询商品折扣表,如果不为空即该二级分类在打折
					String result1 = proDiscountDao.search(proCategoryId.toString()).getDiscountRate().toString();
					result = Integer.parseInt(result1);	//强制类型转换
				}
				//如果折扣表中二级分类不打折,则查看一级分类是否打折
				else {
					proCategoryId = proInfoDao.getDetail(proId.toString()).getOneCategoryId();	//得到它的所属的一级分类ID
					if(proDiscountDao.search(proCategoryId.toString()) != null) {	//根据分类id查看商品折扣表，如果折扣表里有该折扣
						
						String result1 = proDiscountDao.search(proCategoryId.toString()).getDiscountRate().toString();
						result = Integer.parseInt(result1);	//强制类型转换
						
					}
					else {	//如果折扣表里无此分类折扣
						if(proDiscountDao.search("0") != null)	{	//判断是否全场打折,如果全场打折
							String result1 = proDiscountDao.search("0").getDiscountRate().toString();
							result = Integer.parseInt(result1);	//强制类型转换
						}
						//如果也没有全场的折扣
						else
							result = 0;	//不打折返回0
					}
				}
			}
			//如果商品不属于二级分类下，无二级分类id，直属于一级分类
			else{
				Integer proCategoryId = proInfoDao.getDetail(proId.toString()).getOneCategoryId();	//得到它的所属的一级分类ID
				if(proDiscountDao.search(proCategoryId.toString()) != null) {	//根据分类id查看商品折扣表，如果折扣表里有该折扣
					
					String result1 = proDiscountDao.search(proCategoryId.toString()).getDiscountRate().toString();
					result = Integer.parseInt(result1);	//强制类型转换
					
				}
				else {	//如果折扣表里无此分类折扣
					if(proDiscountDao.search("0") != null)	{	//判断是否全场打折,如果全场打折
						String result1 = proDiscountDao.search("0").getDiscountRate().toString();
						result = Integer.parseInt(result1);	//强制类型转换
					}
					//如果也没有全场的折扣
					else
						result = 0;	//不打折返回0
				}
			}
			
		}
		//如果商品有折扣则直接返回该商品的折扣
		else
			result = proInfoDao.getDetail(proId.toString()).getDiscountRate();
		return result;
	}

	//根据商品id查询收益率
	@Override
	public Integer getYieldRateById(String id) {
		// TODO Auto-generated method stub
		Integer twoCategory = proInfoDao.getDetail(id).getTwoCategoryId();
		Integer oneCategory = proInfoDao.getDetail(id).getOneCategoryId();
		String result = null;
		if(proDiscountDao.search(twoCategory.toString()) == null) {
			if(proDiscountDao.search(oneCategory.toString()) == null) {	
				result = proDiscountDao.search("0").getYieldRate().toString();	//分类ID为0表示全场商品
			}else {
				result = proDiscountDao.search(oneCategory.toString()).getYieldRate().toString();
			}
		}else {
			result = proDiscountDao.search(twoCategory.toString()).getYieldRate().toString();
		}
		return Integer.parseInt(result);
	}

}
