package whut.service;


import java.util.List;

import whut.pojo.ProductCategory;
import whut.utils.ResponseData;


public interface ProCategoryService {

	public ResponseData getList();

	public ResponseData add(ProductCategory productCategory);
	
	public ResponseData modify(ProductCategory productCategory);

	public ResponseData delete(String id);

	public ProductCategory ifCategoryExist(String categoryCode);

	public List<ProductCategory> ifHaveChild(String id);

	public ResponseData deleteConfirm(String id);

	public ResponseData getListByParentId(String id);

	public ResponseData modifyStatusNoShow(String id);

}
