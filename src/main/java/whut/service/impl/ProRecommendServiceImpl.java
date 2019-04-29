package whut.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ProInfoDao;
import whut.pojo.ProductInfoForSearch;
import whut.service.ProRecommendService;
import whut.utils.ResponseData;
import whut.utils.SolrJUtil;

@Service
public class ProRecommendServiceImpl implements ProRecommendService {
	
	@Autowired
	private ProInfoDao proInfoDao;
	
	private HttpSolrClient solrClient = SolrJUtil.getSolrClient();
	private String coreName = SolrJUtil.getCoreName();//创建的内核名
	
	@Override
	public ResponseData updateSolrData() {
		try{
			this.updateData();
			return new ResponseData(400,"success",null);
		}catch(Exception e) {
			return new ResponseData(200,"success",null);
		}
	}
	
	
	private void updateData() {
		List<ProductInfoForSearch> productInfoForSearchs = new ArrayList<ProductInfoForSearch>();
		productInfoForSearchs = proInfoDao.getSolrDoucumentList();
		//productInfoForSearchs.add(new ProductInfoForSearch(1, "Fashion hat", "", 1,1, 1, "", "", 1,1, 1, 21, null, "",1, null, null,12, 12.0, 11.0,11.0, 11, 11, 0));
		if(!productInfoForSearchs.isEmpty()) {
			deleteData();
		}else {
			return;
		}
		try {
			solrClient.addBeans(coreName,productInfoForSearchs);
			solrClient.commit(coreName);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteData() {
		try {
			solrClient.deleteByQuery(coreName,"*:*");
			solrClient.commit(coreName);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
