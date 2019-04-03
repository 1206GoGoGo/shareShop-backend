package whut.dao;

import java.util.List;

import whut.pojo.OrderCart;

public interface OrderCartDao {

	List<OrderCart> getListByUser(int id);

	int getAmountById(int id);

}