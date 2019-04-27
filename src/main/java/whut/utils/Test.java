package whut.utils;

import whut.service.impl.MemberInfoServiceImpl;
import whut.service.impl.MemberOrderServiceImpl;

/**
 * 测试工具
 * @author chen cheng
 *
 */
public class Test {
	public static void main(String[] args) {//new String[] {"productId", "oneCategoryId"}
		//SolrJUtil.search(1, 2, "*:*",new String[] {"productId", "attributeList"},null,null,null);
		//SolrJUtil.updateData();
		//SolrJUtil.deleteData();
		System.out.println(SolrJUtil.getScoreById(11));
		
	}
}