package whut.service;

import whut.pojo.ProductDiscount;
import whut.utils.ResponseData;

public interface CommonManageService {

	ResponseData getDiscount(int category);

	ResponseData modifyDiscount(ProductDiscount productDiscount);

}
