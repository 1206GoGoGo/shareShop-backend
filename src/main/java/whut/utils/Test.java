package whut.utils;

import whut.service.impl.MemberInfoServiceImpl;
import whut.service.impl.MemberOrderServiceImpl;

/**
 * 测试工具
 * @author chen cheng
 *
 */
public class Test {
	public static void main(String[] args) {
		//SolrJUtil.search(1, 2, "*:*", null ,new String[] {"productId", "oneCategoryId"});
		//SolrJUtil.updateData();
		//SolrJUtil.deleteData();
		SolrJUtil.search(1,2,"productName:hahha",new String[] {"productId", "productName","oneCategoryId","twoCategoryId","threeCategoryId"},null,null,null);
		
	}
}