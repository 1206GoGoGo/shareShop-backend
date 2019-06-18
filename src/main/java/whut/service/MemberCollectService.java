package whut.service;

import whut.utils.ResponseData;

public interface MemberCollectService {

	ResponseData getListByUser();
	
	ResponseData getIsCollected(int productId);

	ResponseData getAmountById(int id);

	ResponseData collectOrNot(String jsonString);
	
	ResponseData delete(String jsonString);
	
	//获取当前登录用户收藏商品数
	public Integer getCollectAmountByUser();


}
