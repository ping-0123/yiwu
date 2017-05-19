package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;

public interface OrderYzwDao extends IBaseDao<OrderYzw, String>  {
	public String find_last_id();
	
	public boolean isCustomerFirstOrder(OrderYzw order);

	public float get_effective_brockerage_base(OrderYzw order);

	public List<OrderYzw> find_produce_commission_orders();

	public List<OrderYzw> findByCustomer(CustomerYzw customer);

	
	
}
