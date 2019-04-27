package whut.service;

import whut.utils.ResponseData;


public interface ProInfoService {

	public ResponseData getList(Integer pageindex, Integer pagesize);

	public ResponseData getDetail(String id);

	public ResponseData search(String name,Integer pageindex, Integer pagesize);

	public ResponseData getListByCategory(String id,Integer pageindex, Integer pagesize);

	public ResponseData getDetailByCode(String id);


}
