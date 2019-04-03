package whut.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.ProductComment;
import whut.service.ProCommentService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/pro/comment")
public class ProCommentController {
	
	@Autowired
	public ProCommentService proCommentService;
	
	//根据商品id获取评论列表
	@RequestMapping(value = "/getListByProduct", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByProduct(String id, int pageindex, int pagesize) {
		return proCommentService.getListByProduct(id,pageindex, pagesize);
	}
	
	//根据用户id查询用户评论列表
	@RequestMapping(value = "/getListByUser", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByUser(String id, int pageindex, int pagesize) {
		return proCommentService.getListByUser(id,pageindex, pagesize);
	}
	
	//新增评论第一次
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody ResponseData add(@RequestBody ProductComment productComment) {
		return proCommentService.add(productComment);
		
	}
	
	//根据评论id追加评论第二次
	@RequestMapping(value = "/addAgain", method = RequestMethod.GET)
	public @ResponseBody ResponseData addAgain(String id, String content) {
		return proCommentService.addAgain(id, content);
	}
	
	//删除评论,修改评论状态
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public @ResponseBody ResponseData delete(String id){
		return proCommentService.delete(id);
		
	}
	
	//管理员根据商品名称、商品编码、用户名称联合查询
	@RequestMapping(value = "/getListByCondition", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByCondition(String proName,String proCode, String userName, int pageindex, int pagesize){
		return proCommentService.getListByCondition(proName,proCode,userName,pageindex, pagesize);
		
	}
}
