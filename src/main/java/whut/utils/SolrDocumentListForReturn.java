package whut.utils;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class SolrDocumentListForReturn {
	
	private SolrDocumentList solrDocumentList;

	public SolrDocumentListForReturn(SolrDocumentList solrDocumentList) {
		this.solrDocumentList = solrDocumentList;
	}

	@Override
	
	public String toString() {
		String returnString = ",";
	    for (SolrDocument solrDocument : solrDocumentList) {
	    	returnString += "," + solrDocument.jsonStr();
	    }
	    return returnString.substring(2);
	}
}