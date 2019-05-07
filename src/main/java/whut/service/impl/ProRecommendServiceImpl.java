package whut.service.impl;

import org.springframework.stereotype.Service;

import whut.service.ProRecommendService;
import whut.utils.ResponseData;
import whut.utils.SolrJUtil;

@Service
public class ProRecommendServiceImpl implements ProRecommendService {
	
//	@Autowired
//	private ProInfoDao proInfoDao;

	@Override
	public ResponseData recomm(Integer pageindex, Integer pagesize) {
		if(pageindex == null) {pageindex = 1;}
		if(pagesize == null) {pagesize = 20;}
		Object result = SolrJUtil.search(pageindex,pagesize,"*:*",new String[] {"productId", "productName","discountRate","pscore","mainImage","minPrice","maxPrice","description"},null,null,null);
		
		
		return new ResponseData(200,"success",result);
	}
	
	@Override
	public ResponseData detail(Integer proid) {
		Object result = SolrJUtil.search(1,20,"*:*",new String[] {"productId", "productName","discountRate","pscore","mainImage","minPrice","maxPrice","description"},null,null,null);
		
		return new ResponseData(200,"success",result);
	}

	@Override
	public ResponseData search(Integer pageindex, Integer pagesize, String keyWord) {
		if(pageindex == null) {pageindex = 1;}
		if(pagesize == null) {pagesize = 20;}
		Object result = SolrJUtil.search(pageindex,pagesize,"*:*",new String[] {"productId", "productName","discountRate","pscore","mainImage","minPrice","maxPrice","description"},null,null,null);
		
		
		return new ResponseData(200,"success",result);
	}

	@Override
	public ResponseData shopcart(Integer pageindex, Integer pagesize) {
		if(pageindex == null) {pageindex = 1;}
		if(pagesize == null) {pagesize = 20;}
		Object result = SolrJUtil.search(pageindex,pagesize,"*:*",new String[] {"productId", "productName","discountRate","pscore","mainImage","minPrice","maxPrice","description"},null,null,null);
		
		
		return new ResponseData(200,"success",result);
	}


}