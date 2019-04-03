package whut.dao;

import java.util.List;

import java.util.Map;


import whut.pojo.ProductComment;

public interface ProCommentDao {

	public List<ProductComment> getListByProduct(Map<String, Object> map);

	public List<ProductComment> getListByUser(Map<String, Object> map);

	public void add(ProductComment productComment);

	public void delete(String id);

	public void addAgain(Map<String, String> map);

	public ProductComment getCommentById(String id);


}
