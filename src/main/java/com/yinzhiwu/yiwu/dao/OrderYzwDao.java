package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;

public interface OrderYzwDao extends IBaseDao<OrderYzw, String>  {
	public String find_last_id();
	
	public boolean isCustomerFirstOrder(OrderYzw order);

	public float get_effective_brockerage_base(OrderYzw order);

	public List<OrderYzw> find_produce_commission_orders() throws DataNotFoundException;

	public List<OrderYzw> findByCustomer(CustomerYzw customer);

	public List<OrderYzw> findByCustomerId(int customerId);

	public List<String> find_contractNos_by_customer_id(int customerId);

	public Contract find_valid_contract_by_customer_by_subCourseType(int customerId, String subCourseType);

	public OrderYzw findByContractNO(String contractNo) throws DataNotFoundException, YiwuException;
	
	public List<OrderYzw> findAllLastDayOrders();
}
