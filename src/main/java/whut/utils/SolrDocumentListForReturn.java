package whut.utils;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SolrDocumentListForReturn {
	
	private SolrDocumentList solrDocumentList;
	private long numFound;

	public SolrDocumentListForReturn(SolrDocumentList solrDocumentList) {
		this.solrDocumentList = solrDocumentList;
		this.numFound = solrDocumentList.getNumFound();
	}


	/**
	 * 返回带有查询总数的json节点
	 * : {"numFound":3,"indata":[{"productId":11,"attributeList":"hahha"},{"productId":11,"attributeList":"hahha"}]}
	 * @return
	 */
	public ObjectNode toJson() {
	    
	    ObjectMapper mapper = new ObjectMapper();
	    //生成对象结点
	  	ObjectNode objNode = mapper.createObjectNode();
	    
	  	//放总数
	  	objNode.put("numFound", numFound);  //查询到的总数量
	  	
	    //先生成数据节点
	    ArrayNode arrNode = mapper.createArrayNode();
	    for (SolrDocument solrDocument : solrDocumentList) {
	    	
	    	//再加一层
	    	ObjectNode objNode2 = mapper.createObjectNode();
	    	//遍历放入
	    	for(String name: solrDocument.getFieldNames()) {
	    		try {
	    			int i = Integer.parseInt(solrDocument.getFieldValue(name).toString());
	    			objNode2.put(name, i);
	    		}catch(Exception e){
	    			objNode2.put(name, solrDocument.getFieldValue(name).toString());
	    		}
	    	}
	    	
	    	arrNode.add(objNode2);  //一个
	    }
	    //放数据
	    objNode.set("indata", arrNode);

	    return objNode;
	}
}