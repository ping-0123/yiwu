package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.OrderDao;
import com.yinzhiwu.yiwu.entity.yzwOld.Customer;
import com.yinzhiwu.yiwu.entity.yzwOld.Order;
import com.yinzhiwu.yiwu.model.view.OrderOldApiView;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order, String> implements OrderDao {

	@Override
	public List<Order> findByCustomer(Customer c) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findValidOrderByCustomer(Customer c) {

		String hql = "FROM Order where checkedStatus IN ('已审核','未审核', '未通过','未确认')" + "AND customer_id = :customerId";
		return (List<Order>) getHibernateTemplate().findByNamedParam(hql, "customerId", c.getId());
	}

	@Override
	public int findInterestedStore(Customer c) {
		// String hql="FROM Order where checkedStatus IN ('已审核','未审核',
		// '未通过','未确认')"
		// + "AND customer_id = :customerId";
		return 0;
	}

	@Override
	public int findAttendedStudentCount(String courseId) {
		String hql = "SELECT count(*) from Order where courseId = :courseId";
		@SuppressWarnings("unchecked")
		List<Long> l = (List<Long>) getHibernateTemplate().findByNamedParam(hql, "courseId", courseId);
		if (l.size() > 0)
			return l.get(0).intValue();
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getMonthlyRevenue(int districtId, int productType, Date start, Date end) {
		StringBuilder hql = new StringBuilder();
		String orginHql = "SELECT " + "	    t1.storeId, t3.deptName, t1.payedDate, SUM(t1.payedAmount)" + "	FROM"
				+ "	    Order t1" + "	        LEFT JOIN"
				+ "	    ProductTypeRelation t2 ON t1.productId = t2.productId" + "	        LEFT JOIN"
				+ "	    Department t3 ON (t1.storeId = t3.id)" + "	WHERE"
				+ "	    t1.payedDate BETWEEN :start AND :end";
		hql.append(orginHql);
		if (districtId > 0) {
			hql.append(" AND t3.superiorId = " + districtId);
		}
		if (productType > 0) {
			hql.append(" AND t2.type.id = " + productType);
		}

		hql.append(" GROUP BY t1.storeId , t1.payedDate");
		hql.append(" ORDER BY t1.payedDate,t3.deptName ");
		/*
		 * +"	        AND t3.superiorId = 113"
		 * +"	GROUP BY t1.storeId , t1.payedDate";
		 */

		return (List<Object[]>) getHibernateTemplate().findByNamedParam(hql.toString(), new String[] { "start", "end" },
				new Date[] { start, end });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderOldApiView> findDailyOrderByStore(int storeId, Date payedDate, int productTypeId) {
		String hql = "select  t1.createTime, t2.name,t1.markedPrice,t1.count,t3.amount, t4.name, t5.name, t5.auditOrChild,t1.vipAttr, t6.name"
				+ "	from Order t1 left join Product t2 on(t1.productId=t2.id)"
				+ "		left join OrderPayedMethod t3 on(t1.id= t3.orderId)"
				+ "	    left join PayedMethod t4 on(t3.payedMethodId = t4.id)"
				+ "	    left join Customer t5 on (t1.customerId = t5.id)"
				+ "		left join Employee t6 on(t1.createUserId= t6.id)"
				+ "		left join ProductTypeRelation t7 on(t1.productId=t7.productId)"
				+ "	 where t1.payedDate = :payedDate  and t1.storeId=:storeId and t7.type.id=:productTypeId";

		List<OrderOldApiView> orders = new ArrayList<>();
		List<Object[]> l = (List<Object[]>) getHibernateTemplate().findByNamedParam(hql,
				new String[] { "payedDate", "storeId", "productTypeId" },
				new Object[] { payedDate, storeId, productTypeId });

		for (Object[] objs : l) {
			OrderOldApiView o = new OrderOldApiView();
			o.setRecordDate((java.sql.Date) objs[0]);
			o.setProductName((String) objs[1]);
			if (objs[2] instanceof Float)
				o.setMarckedPrice((float) objs[2]);
			if (objs[3] instanceof Integer) {
				o.setCount((int) objs[3]);
			}
			if (objs[4] instanceof Float)
				o.setPayedAmount((float) objs[4]);
			o.setPayedMethod((String) objs[5]);
			o.setCustomerName((String) objs[6]);
			o.setAuditOrChild((String) objs[7]);
			o.setVipAttr((String) objs[8]);
			o.setSalesmanName((String) objs[9]);
			orders.add(o);

		}

		return orders;
	}

	@Override
	public List<Order> findValidOrders(int customerId, String subType) {
		
		return findByProperties(
				new String[]{"customerId", "productSubType", "checkedStatus"},  
				new Object[]{customerId, subType, "已审核"});
	}

	@Override
	public List<OrderOldApiView> findDailyOrderByStore(int storeId, Date date) {
		String hql = "select  t1.createTime, t2.name,t1.markedPrice,t1.count,t3.amount, t4.name, t5.name, t5.auditOrChild,t1.vipAttr, t6.name"
				+ "	from Order t1 left join Product t2 on(t1.productId=t2.id)"
				+ "		left join OrderPayedMethod t3 on(t1.id= t3.orderId)"
				+ "	    left join PayedMethod t4 on(t3.payedMethodId = t4.id)"
				+ "	    left join Customer t5 on (t1.customerId = t5.id)"
				+ "		left join Employee t6 on(t1.createUserId= t6.id)"
				+ "	 where t1.payedDate = :payedDate  and t1.storeId=:storeId";

		List<OrderOldApiView> orders = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<Object[]> l = (List<Object[]>) getHibernateTemplate().findByNamedParam(hql,
				new String[] { "payedDate", "storeId" }, new Object[] { date, storeId });

		for (Object[] objs : l) {
			OrderOldApiView o = new OrderOldApiView();
			o.setRecordDate((java.sql.Date) objs[0]);
			o.setProductName((String) objs[1]);
			if (objs[2] instanceof Float)
				o.setMarckedPrice((float) objs[2]);
			if (objs[3] instanceof Integer) {
				o.setCount((int) objs[3]);
			}
			if (objs[4] instanceof Float)
				o.setPayedAmount((float) objs[4]);
			o.setPayedMethod((String) objs[5]);
			o.setCustomerName((String) objs[6]);
			o.setAuditOrChild((String) objs[7]);
			o.setVipAttr((String) objs[8]);
			o.setSalesmanName((String) objs[9]);
			orders.add(o);

		}

		return orders;
	}
}
