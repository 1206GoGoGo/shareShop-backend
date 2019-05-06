package whut.dao;

import java.util.List;

import java.util.Map;


import whut.pojo.ProductComment;

public interface ProCommentDao {

	//根据商品id获取评论列表
	public List<ProductComment> getListByProduct(Map<String, Object> map);

	//根据用户id查询用户评论列表
	public List<ProductComment> getListByUser(Map<String, Object> map);

	//新增评论第一次
	public void add(ProductComment productComment);

	//根据评论id追加评论第二次
	public void addAgain(Map<String, String> map);

	//根据评论id查看评论详情
	public ProductComment getCommentById(String id);

	//根据商品id查询商品评论总数
	public Integer getListByProductNum(String id);

	//根据用户id查询用户评论总数
	public Integer getListByUserNum(String id);

}
