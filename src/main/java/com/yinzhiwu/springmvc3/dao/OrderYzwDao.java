package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

public interface OrderYzwDao extends IBaseDao<OrderYzw, String>  {
	public String find_last_id();
	
	public boolean isCustomerFirstOrder(OrderYzw order);

	public float get_effective_brockerage_base(OrderYzw order);

	public List<OrderYzw> find_produce_commission_orders() throws DataNotFoundException;

	public List<OrderYzw> findByCustomer(CustomerYzw customer);

	public List<OrderYzw> findByCustomerId(int customerId);

	public List<String> find_contractNos_by_customer_id(int customerId);

	public List<OrderYzw> find_valid_orders_by_customer_by_subCourseType(int customerId, String subCourseType);
	
	
}
