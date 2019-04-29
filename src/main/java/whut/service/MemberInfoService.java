package whut.service;


import whut.pojo.UserInfo;
import whut.utils.ResponseData;

public interface MemberInfoService {

	public ResponseData memberAdd(UserInfo user);

	public ResponseData delete();

	public ResponseData modify(UserInfo user);

	public ResponseData getDetail();

	public ResponseData getMemberListBySeller(Integer pagesize, Integer pageindex, Integer status);

	public ResponseData getCountAWeek();

	public ResponseData modifyPhone(String jsonString);

	public ResponseData modifyEmail(String jsonString);

	public ResponseData modifyPassword(String jsonString);
}
