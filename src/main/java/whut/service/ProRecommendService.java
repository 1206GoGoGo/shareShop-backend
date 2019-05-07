package whut.service;

import whut.utils.ResponseData;

public interface ProRecommendService {

	ResponseData recomm(Integer pageindex, Integer pagesize);

	ResponseData detail(Integer proid);

	ResponseData search(Integer pageindex, Integer pagesize, String keyWord);

	ResponseData shopcart(Integer pageindex, Integer pagesize);

}
