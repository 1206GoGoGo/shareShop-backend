package whut.service;


import java.util.List;

import whut.pojo.ProductCategory;
import whut.utils.ResponseData;


public interface ProCategoryService {

	public ResponseData getList();

	public ProductCategory ifCategoryExist(String categoryCode);

	public List<ProductCategory> ifHaveChild(String id);

	public ResponseData getListByParentId(String id);
}
