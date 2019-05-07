package whut.service;

import java.math.BigDecimal;

import whut.utils.ResponseData;

public interface StateTaxService {

	ResponseData getStateTaxList(Integer pageindex, Integer pagesize);

	ResponseData getStateTaxById(String id);

	ResponseData getStateTaxByName(String name);

	BigDecimal getTaxById(String id);

}
