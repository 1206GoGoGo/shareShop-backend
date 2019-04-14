package whut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.UserAddrDao;
import whut.pojo.UserAddr;
import whut.service.MemberAddressService;
import whut.utils.ResponseData;
import whut.utils.SysContent;
@Service
public class MemberAddressServiceImpl implements MemberAddressService {
	@Autowired
	private UserAddrDao dao;

	@Override
	public ResponseData getListByUserId() {
		List<UserAddr> userAddr = dao.getListByUserId(SysContent.getUserId());
		if(userAddr.isEmpty()) {
			return new ResponseData(400,"no data satify request",null);
		}else {
			return new ResponseData(200,"success",userAddr);
		}
	}

	/**
	 * 仅可修改部分信息
	 */
	@Override
	public ResponseData modify(UserAddr userAddr) {
		UserAddr userAddrOld = dao.getAddrByAddrId(userAddr.getUserAddrId());
		if(userAddrOld == null) {
			return new ResponseData(406,"the userAddress does not exist",null);
		}
		if(userAddrOld.getUserId()!=SysContent.getUserId()) {
			//非法访问，该收货地址不属于该用户
			return new ResponseData(403,"illegally accessed",null);
		}
		if(userAddr.getIsDefault()==1) {
			//设置默认，同时取消其它默认值
			dao.modifyDefault(userAddr.getUserAddrId());
		}
		userAddr.setUserId(userAddr.getUserAddrId());
		java.util.Date modifiedTime = new java.util.Date();
		userAddr.setModifiedTime(modifiedTime);
		dao.modifyAddr(userAddr);
		return new ResponseData(null);
	}

	@Override
	public ResponseData add(UserAddr userAddr) {
		if(userAddr.getIsDefault()==1) {
			//设置默认，同时取消其它默认值
			dao.modifyDefault(userAddr.getUserAddrId());
		}
		userAddr.setUserId(userAddr.getUserAddrId());
		java.util.Date modifiedTime = new java.util.Date();
		userAddr.setModifiedTime(modifiedTime);
		dao.addAddr(userAddr);
		return new ResponseData(null);
	}

	@Override
	public ResponseData delete(int userAddrId) {
		UserAddr userAddrOld = dao.getAddrByAddrId(userAddrId);
		if(userAddrOld == null) {
			return new ResponseData(406,"the userAddress does not exist",null);
		}
		if(userAddrOld.getUserId()!=SysContent.getUserId()) {
			//非法访问，该收货地址不属于该用户
			return new ResponseData(403,"illegally accessed",null);
		}
		dao.delete(userAddrId);
		return new ResponseData(null);
	}

}
