package whut.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.UserCollectDao;
import whut.pojo.UserCollect;
import whut.service.MemberCollectService;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;
import whut.utils.SysContent;
@Service
public class MemberCollectServiceImpl implements MemberCollectService {

	@Autowired
	private UserCollectDao dao;

	@Override
	public ResponseData getListByUser() {
		List<UserCollect> list = dao.getListByUser(SysContent.getUserId());
		if(list.isEmpty()) {
			return new ResponseData(400,"no data satify request",null);
		}else {
			return new ResponseData(200,"success",list);
		}
	}

	@Override
	public ResponseData getAmountById(int id) {
		return new ResponseData(200,"success",dao.getAmountById(id));
	}

	@Override
	public ResponseData add(String jsonString) {

		JsonUtils jsonUtils = new JsonUtils(jsonString);
		int productId = jsonUtils.getIntValue("productId");
		
		Map<String, Integer> map = new HashMap<>();
		map.put("productId", productId);
		map.put("userId", SysContent.getUserId());
        UserCollect userCollect = dao.getCollect(map);
		if(userCollect!=null) {
			return new ResponseData(406,"the merchandise has been collected",null);
		}
        userCollect = new UserCollect();
		userCollect.setUserId(SysContent.getUserId());
		userCollect.setProductId(productId);
		userCollect.setCollectTime(new java.util.Date());
		dao.add(userCollect);
		return new ResponseData(null);
	}

	@Override
	public ResponseData delete(String jsonString) {

		JsonUtils jsonUtils = new JsonUtils(jsonString);
		int collectId = jsonUtils.getIntValue("collectId");
		
        UserCollect userCollect = dao.getCollectByCollectId(collectId);
		if(userCollect==null) {
			return new ResponseData(406,"the merchandise has not been collected",null);
		}
		if(userCollect.getUserId()!=SysContent.getUserId()) {
			return new ResponseData(403,"illegally accessed",null);
		}
		dao.delete(collectId);
		return new ResponseData(null);
	}
}
