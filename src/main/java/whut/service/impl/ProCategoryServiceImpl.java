package whut.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ProCategoryDao;
import whut.pojo.ProductCategory;
import whut.service.ProCategoryService;
import whut.utils.ResponseData;


@Service
public class ProCategoryServiceImpl implements ProCategoryService{

	@Autowired
	public ProCategoryDao proCategoryDao;
	
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
	public ResponseData add(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		if(proCategoryDao.ifCategoryExist(productCategory.getCategoryCode()) == null) {
			proCategoryDao.add(productCategory);
			return new ResponseData(200,"add success",null);
		}
		return new ResponseData(406,"Fail to add",null);
	}

	@Override
	public ResponseData modify(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		proCategoryDao.modify(productCategory);
		return new ResponseData(200,"modify success",null);
	}

	@Override
	public ResponseData delete(String id) {
		// TODO Auto-generated method stub
		List<ProductCategory> list = new ArrayList<>();
		list = proCategoryDao.ifHaveChild(id);
		if(list.size() == 0) {
			proCategoryDao.delete(id);
			return new ResponseData(200,"delete success",null);
		}
		return new ResponseData(406,"There are subcategories under this category",null);
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
	public ResponseData deleteConfirm(String id) {
		// TODO Auto-generated method stub
		proCategoryDao.delete(id);
		List<ProductCategory> list = new ArrayList<>();
		list = proCategoryDao.ifHaveChild(id);
		for(int i = 0; i < list.size(); i++) {
			proCategoryDao.delete(list.get(i).getCategoryId().toString());
			List<ProductCategory> list1 = new ArrayList<>();
			list1 = proCategoryDao.ifHaveChild(list.get(i).getCategoryId().toString());
			if(list1.size() > 0) {
				for(int j = 0; j < list1.size(); j++) {
					proCategoryDao.delete(list1.get(j).getCategoryId().toString());
				}
			}
		}
		return new ResponseData(200,"delete success",null);
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

	@Override
	public ResponseData modifyStatusNoShow(String id) {
		// TODO Auto-generated method stub
		proCategoryDao.modifyStatusNoShow(id);
		return new ResponseData(200,"success",null);
	}

}
