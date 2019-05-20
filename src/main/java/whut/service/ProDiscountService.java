package whut.service;

import java.math.BigDecimal;

import whut.pojo.ProductDiscount;
import whut.utils.ResponseData;



public interface ProDiscountService {

	public ResponseData getList(Integer pageindex, Integer pagesize);

	public ResponseData search(String id);

	ResponseData add(ProductDiscount productDiscount);

	ResponseData modify(ProductDiscount productDiscount);

	public BigDecimal getDiscountRateById(String id);

	public BigDecimal getYieldRateById(String id);

}
