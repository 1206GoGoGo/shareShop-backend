package whut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ProSpecsDao;
import whut.pojo.ProductSpecs;
import whut.service.ProSpecsService;
import whut.utils.ResponseData;

@Service
public class ProSpecsServiceImpl implements ProSpecsService {

	@Autowired
	private ProSpecsDao proSpecsDao;
	
	@Override
	public ResponseData getProSpecsByProId(String id) {
		// TODO Auto-generated method stub
		List<ProductSpecs> list =  proSpecsDao.getProSpecsByProId(id);
		if(list != null) {
			return new ResponseData(200,"success",list);
		}
		else
			return new ResponseData(400,"No data",null);
	}

	@Override
	public ResponseData getProSpecsById(String id) {
		// TODO Auto-generated method stub
		ProductSpecs productSpecs = proSpecsDao.getProSpecsById(id);
		if(productSpecs != null) {
			return new ResponseData(200,"success",productSpecs);
		}
		else
			return new ResponseData(400,"No data",null);

	}

	@Override
	public ResponseData addProSpecs(ProductSpecs productSpecs) {
		// TODO Auto-generated method stub
		proSpecsDao.addProSpecs(productSpecs);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData modifyProSpecs(ProductSpecs productSpecs) {
		// TODO Auto-generated method stub
		proSpecsDao.modifyProSpecs(productSpecs);
		return new ResponseData(200,"success",null);
	}

}
