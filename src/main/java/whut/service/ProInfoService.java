package whut.service;



import whut.pojo.ProductInfo;
import whut.utils.ResponseData;


public interface ProInfoService {

	public ResponseData getList(int pageindex, int pagesize);

	public ResponseData getDetail(String id);

	public ResponseData add(ProductInfo productInfo);

	public ResponseData search(String name,int pageindex, int pagesize);

	public ResponseData modify(ProductInfo productInfo);

	public ResponseData modifyAuditStatus(String id, String status);

	public ResponseData modifyShelfStatus(String id, String status);

	public ResponseData getListByCategory(String id,int pageindex, int pagesize);

	public ResponseData getDetailByCode(String id);

	public ResponseData getIfcheckedList(String status,int pageindex, int pagesize);

	public ResponseData getIfShelfList(String status,int pageindex, int pagesize);

	public ResponseData getListByCondition(String name, String procode, String categoryId1, String categoryId2,
			String categoryId3, String status1, String status2,int pageindex, int pagesize);

	

}
