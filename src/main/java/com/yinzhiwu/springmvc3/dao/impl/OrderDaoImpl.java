package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.dao.OrderDao;
import com.yinzhiwu.springmvc3.entity.Course;
import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.entity.Order;


@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order, String> implements OrderDao {
	

	@Override
	public List<Order> findByProperty(String propertyName, Object value) {
		return null;
	}

	@Override
	public List<Order> findByCustomer(Customer c) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findValidOrderByCustomer(Customer c) {
		
		
		String hql="FROM Order where checkedStatus IN ('已审核','未审核', '未通过','未确认')"
				+ "AND customer_id = :customerId";
		return (List<Order>) getHibernateTemplate().findByNamedParam(hql, "customerId", c.getId());
	}

	@Override
	public int findInterestedStore(Customer c){
		String hql="FROM Order where checkedStatus IN ('已审核','未审核', '未通过','未确认')"
				+ "AND customer_id = :customerId";
		return 0;
	}

	@Override
	public int findAttendedStudentCount(String courseId) {
		String hql="SELECT count(*) from Order where courseId = :courseId";
		@SuppressWarnings("unchecked")
		List<Long> l = (List<Long>) getHibernateTemplate().findByNamedParam(hql, "courseId",courseId);
		if(l.size()>0)
			return l.get(0).intValue();
		return 0;
	}
	
}
