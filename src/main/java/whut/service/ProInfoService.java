package whut.service;


import whut.utils.ResponseData;


public interface ProInfoService {

	public ResponseData getList(Integer pageindex, Integer pagesize);
	
	//获取推荐商品
	public ResponseData getRecommendPro(); 
	
	public ResponseData getPicList(String id);

	public ResponseData getDetail(String id);

	public ResponseData search(String name,Integer pageindex, Integer pagesize,String field,Byte judge);

	public ResponseData getListByCategory(String id,Integer pageindex, Integer pagesize);

	public ResponseData getDetailByCode(String id);

	public ResponseData getListSearch(Integer pageindex, Integer pagesize,String field,Byte judge);

	public ResponseData getListByCategorySearch(String id, Integer pageindex, Integer pagesize,String field,Byte judge);


}
