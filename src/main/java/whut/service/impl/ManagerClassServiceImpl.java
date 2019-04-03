package whut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ManagerCategoryDao;
import whut.dao.UserLoginDao;
import whut.pojo.ManagerCategory;
import whut.service.ManagerClassService;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;

@Service
public class ManagerClassServiceImpl implements ManagerClassService {
	
	@Autowired
	private ManagerCategoryDao dao;
	
	@Autowired
	private UserLoginDao userLoginDao;
	
	@Override
	public ResponseData getList() {
		List<ManagerCategory> list = dao.getList();
		if(list.isEmpty()) {
			return new ResponseData(400,"no data",null);
		}else {
			return new ResponseData(200,"success",list);
		}
		
	}

	@Override
	public ResponseData add(ManagerCategory managerCategory) {
		//判断同名
		ManagerCategory managerCategoryOld = null;
		try {
			managerCategoryOld = dao.getIdByName(managerCategory.getName());
		}catch(Exception e) {
			//不存在该分类名
			dao.add(managerCategory);
			return new ResponseData(200,"success",null);
		}
		//已存在该分类名
		return new ResponseData(406,"class name already exists",null);

	}

	@Override
	public ResponseData modify(ManagerCategory managerCategory) {
		//判断修改的是不是同名
		ManagerCategory managerCategoryOld = null;
		try {
			managerCategoryOld = dao.getIdByName(managerCategory.getName());
			if(managerCategory.getCategoryId() != managerCategoryOld.getCategoryId()) {
				return new ResponseData(406,"class name already exists",null);
			}else {
				dao.modify(managerCategory);
				return new ResponseData(200,"success",null);		
			}
		}catch(Exception e) {
			//不存在同名分类
			dao.modify(managerCategory);
			return new ResponseData(200,"success",null);
		}

	}

	@Override
	public ResponseData delete(String jsonString) {
		int id = new JsonUtils(jsonString).getIntValue("id");
		System.out.println(id);
		ManagerCategory managerCategory = dao.getByCategoryId(id);
		if(managerCategory == null) {
			//要删除分类不存在
			return new ResponseData(4061,"classification does not exist",null);
		}
		System.out.println(managerCategory.getLevel());
		int amount = userLoginDao.getLoginInfoAmountByLevel(managerCategory.getLevel());
		if(amount!=0) {
			//该分类下有管理员，无法修改
			return new ResponseData(4062,"classification binds data",null);
		}
		dao.delete(id);
		return new ResponseData(200,"success",null);
	}

}
