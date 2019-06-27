package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.ProductInfo;
import whut.pojo.ProductInfoForSearch;

public interface ProInfoDao {

	//获取所有商品列表
	List<ProductInfo> getList(Map<String, Object> map);
	
	//获取推荐商品列表
	List<ProductInfo> getRecommendPro();

	//根据商品id获取某商品详情
	ProductInfo getDetail(String id);

	//根据商品名称查找商品
	//List<ProductInfo> search(@Param(value = "name") String name);
	List<ProductInfo> search(Map<String, Object> map);

	//根据分类获取商品列表
	List<ProductInfo> getListByCategory(Map<String, Object> map);

	//根据商品码id获取某商品详情
	ProductInfo getDetailByCode(String id);

	//获取全部商品信息solr所有信息【参考ProductInfoForSearch pojo对象】与ProductInfo相比删除了productSpecs，暂时新增6个参数
	List<ProductInfoForSearch> getSolrDoucumentList();

	Integer getListNum();	//获取所有商品列表时返回查询结果总数

	Integer getListByCategoryNum(String id);	//根据分类获取商品列表时返回查询结果总数

	

}
