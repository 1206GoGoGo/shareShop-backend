package whut.service;


import whut.pojo.ManagerInfo;
import whut.utils.ResponseData;

public interface ManagerInfoService {

	public ResponseData getList(int status);

	public ResponseData add(ManagerInfo managerInfo);

	public ResponseData getDetail(int id);

	public ResponseData modify(ManagerInfo managerInfo);

	public ResponseData delete(String jsonString);

}
