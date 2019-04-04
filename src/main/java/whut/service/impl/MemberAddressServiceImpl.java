package whut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.UserAddrDao;
import whut.pojo.UserAddr;
import whut.service.MemberAddressService;
import whut.utils.ResponseData;
@Service
public class MemberAddressServiceImpl implements MemberAddressService {
	@Autowired
	private UserAddrDao dao;

	@Override
	public ResponseData getListByUserId(int id) {
		List<UserAddr> userAddr = dao.getListByUserId(id);
		if(userAddr.isEmpty()) {
			return new ResponseData(400,"no data satify request",null);
		}else {
			return new ResponseData(200,"success",userAddr);
		}
	}

}
