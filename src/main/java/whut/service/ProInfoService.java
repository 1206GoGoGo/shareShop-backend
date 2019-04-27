package whut.service;

import whut.utils.ResponseData;


public interface ProInfoService {

	public ResponseData getList(int pageindex, int pagesize);

	public ResponseData getDetail(String id);

	public ResponseData search(String name,Integer pageindex, Integer pagesize);

	public ResponseData getListByCategory(String id,int pageindex, int pagesize);

	public ResponseData getDetailByCode(String id);


}
