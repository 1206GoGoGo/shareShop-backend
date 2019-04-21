package whut.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ProCategoryDao;
import whut.pojo.ProductCategory;
import whut.service.ProCategoryService;
import whut.utils.ResponseData;


@Service
public class ProCategoryServiceImpl implements ProCategoryService{

	@Autowired
	private ProCategoryDao proCategoryDao;
	
	@Override
	public ResponseData getList() {
		// TODO Auto-generated method stub		
		List<ProductCategory> list = proCategoryDao.getList();
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ProductCategory ifCategoryExist(String categoryCode) {
		// TODO Auto-generated method stub
		return proCategoryDao.ifCategoryExist(categoryCode);
	}

	@Override
	public List<ProductCategory> ifHaveChild(String id) {
		// TODO Auto-generated method stub
		return proCategoryDao.ifHaveChild(id);
	}


	@Override
	public ResponseData getListByParentId(String id) {
		// TODO Auto-generated method stub
		List<ProductCategory> list = new ArrayList<>();
		list = proCategoryDao.ifHaveChild(id);
		if(list.size() == 0) {
			return new ResponseData(406,"There are no subcategories",null);
		}
		return new ResponseData(200,"success",list);
	}

}
