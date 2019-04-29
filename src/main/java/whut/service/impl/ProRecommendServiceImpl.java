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
	

}
