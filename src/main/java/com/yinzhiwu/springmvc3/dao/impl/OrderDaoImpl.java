package com.yinzhiwu.springmvc3.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.OrderDao;
import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.entity.Order;
import com.yinzhiwu.springmvc3.model.RevenueModel;


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
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getMonthlyRevenue(int districtId, int productType, Date start, Date end){
		StringBuilder hql= new StringBuilder();
		String orginHql="SELECT "
				+"	    t1.storeId, t3.deptName, t1.payedDate, SUM(t1.payedAmount)"
				+"	FROM"
				+"	    Order t1"
				+"	        LEFT JOIN"
				+"	    ProductTypeRelation t2 ON t1.productId = t2.productId"
				+"	        LEFT JOIN"
				+"	    Department t3 ON (t1.storeId = t3.id)"
				+"	WHERE"
				+"	    t1.payedDate BETWEEN :start AND :end";
		hql.append(orginHql);
		if(districtId >0){
			hql.append(" AND t3.superiorId = " + districtId);
		}
		if(productType>0){
			hql.append(" AND t2.type.id = " + productType);
		}
		
		hql.append(" GROUP BY t1.storeId , t1.payedDate");
		hql.append(" ORDER BY t1.payedDate,t3.deptName ");
		/*
				+"	        AND t3.superiorId = 113"
				+"	GROUP BY t1.storeId , t1.payedDate"; */
		
		return (List<Object[]>) getHibernateTemplate().findByNamedParam(
				hql.toString(), 
				new String[]{"start", "end"},
				new Date[]{start, end});
	}
}
