package whut.service;

import whut.pojo.ManagerCategory;
import whut.utils.ResponseData;

public interface ManagerClassService {

	public ResponseData getList();

	public ResponseData add(ManagerCategory managerCategory);

	public ResponseData modify(ManagerCategory managerCategory);

	public ResponseData delete(String jsonString);
}
