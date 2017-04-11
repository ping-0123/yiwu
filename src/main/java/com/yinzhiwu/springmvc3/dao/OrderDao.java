package com.yinzhiwu.springmvc3.dao;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.entity.Order;

public interface OrderDao {
	 public List<Order> findByProperty(String propertyName, Object value);
	 public List<Order> findByCustomer(Customer c);
	 public List<Order> findValidOrderByCustomer(Customer c);
	int findInterestedStore(Customer c);
	
	int findAttendedStudentCount(String courseId);
	List<Object[]> getMonthlyRevenue(int districtId, int productType, Date start, Date end);
}
