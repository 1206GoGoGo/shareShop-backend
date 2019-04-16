package whut.service;

import whut.pojo.UserAddr;
import whut.utils.ResponseData;

public interface MemberAddressService {
	
	public ResponseData getListByUserId();

	public ResponseData modify(UserAddr userAddr);

	public ResponseData add(UserAddr userAddr);

	public ResponseData delete(int userAddrId);

	public ResponseData getListState();

	public ResponseData getListCity(int stateId);

}
