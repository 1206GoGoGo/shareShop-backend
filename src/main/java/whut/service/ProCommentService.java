package whut.service;



import whut.pojo.ProductComment;
import whut.utils.ResponseData;



public interface ProCommentService {

	public ResponseData getListByProduct(String id,Integer pageindex, Integer pagesize);

	public ResponseData getListByUser(String id,Integer pageindex, Integer pagesize);
	
	public ResponseData add(ProductComment productComment);

	public ResponseData addAgain(String id, String content);

	public ProductComment getCommentById(String id);

}
