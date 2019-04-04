package whut.service;



import whut.pojo.ProductDiscount;
import whut.utils.ResponseData;



public interface ProDiscountService {

	public ResponseData getList(int pageindex, int pagesize);

	public ResponseData search(String id);

	public ResponseData add(ProductDiscount productDiscount);

	public ResponseData modify(ProductDiscount productDiscount);

}
