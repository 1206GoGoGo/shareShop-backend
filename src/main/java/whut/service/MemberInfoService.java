package whut.service;


import whut.pojo.UserInfo;
import whut.utils.ResponseData;

public interface MemberInfoService {

	public ResponseData add(UserInfo user);

	public ResponseData delete(String jsonString);

	public ResponseData modify(UserInfo user);

	public ResponseData getDetail(int id);

	public ResponseData getMemberListBySeller(int pagesize, int pageindex, String username);

	public ResponseData getCountAWeek();
}
