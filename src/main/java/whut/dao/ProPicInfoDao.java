package whut.dao;

import java.util.List;

import whut.pojo.ProductPicInfo;

public interface ProPicInfoDao {
	
	public List<ProductPicInfo> getPicList(String id);

}
