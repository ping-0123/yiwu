package com.yinzhiwu.yiwu.dao;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.yiwu.entity.yzwOld.Customer;
import com.yinzhiwu.yiwu.entity.yzwOld.Order;
import com.yinzhiwu.yiwu.model.view.OrderOldApiView;

public interface OrderDao extends IBaseDao<Order, String> {
	public List<Order> findByCustomer(Customer c);

	public List<Order> findValidOrderByCustomer(Customer c);

	int findInterestedStore(Customer c);

	int findAttendedStudentCount(String courseId);

	List<Object[]> getMonthlyRevenue(int districtId, int productType, Date start, Date end);

	List<OrderOldApiView> findDailyOrderByStore(int storeId, Date date);

	List<Order> findValidOrders(int customerId, String subType);

	List<OrderOldApiView> findDailyOrderByStore(int storeId, Date payedDate, int productTypeId);

}
