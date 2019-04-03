package whut.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ProCommentDao;
import whut.pojo.ProductComment;
import whut.service.ProCommentService;
import whut.utils.ResponseData;



@Service
public class ProCommentServiceImpl implements ProCommentService{

	@Autowired
	public ProCommentDao proCommentDao;

	@Override
	public ResponseData getListByProduct(String id,int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("productId", id);
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<ProductComment> list = proCommentDao.getListByProduct(map);
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData getListByUser(String id,int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("userId", id);
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		List<ProductComment> list = new ArrayList<>();
		list = proCommentDao.getListByUser(map);
		if(list == null)
			return new ResponseData(400,"No Comments",null);
		return new ResponseData(200,"success",list);
	}

	@Override
	public ResponseData add(ProductComment productComment) {
		// TODO Auto-generated method stub
		proCommentDao.add(productComment);
		return new ResponseData(200,"add success",null);
	}

	@Override
	public ResponseData delete(String id) {
		// TODO Auto-generated method stub
		proCommentDao.delete(id);
		return new ResponseData(200,"delete success",null);
	}

	@Override
	public ResponseData addAgain(String id, String content) {
		// TODO Auto-generated method stub
		ProductComment productComment = proCommentDao.getCommentById(id);
		if(productComment.getSecondContent() == null) {
			Map<String, String> map = new HashMap<>();
			map.put("commentId", id);
			map.put("secondContent", content);
			proCommentDao.addAgain(map);
			return new ResponseData(200,"add success",null);
		}
		return new ResponseData(500,"Sorry,you can't evaluate again",null);
		
	}

	@Override
	public ProductComment getCommentById(String id) {
		// TODO Auto-generated method stub
		return proCommentDao.getCommentById(id);
	}

	@Override
	public ResponseData getListByCondition(String proName, String proCode, String userName,int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		//商品评论：管理员要显示商品编码、商品名称、评价的用户名。（新建一个POJO类）
		return null;
	}
	
}
