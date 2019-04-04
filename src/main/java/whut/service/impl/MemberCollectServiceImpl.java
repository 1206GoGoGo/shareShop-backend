package whut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.UserCollectDao;
import whut.pojo.UserCollect;
import whut.service.MemberCollectService;
import whut.utils.ResponseData;
@Service
public class MemberCollectServiceImpl implements MemberCollectService {

	@Autowired
	private UserCollectDao dao;

	@Override
	public ResponseData getListByUser(int id) {
		List<UserCollect> list = dao.getListByUser(id);
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
	

}
