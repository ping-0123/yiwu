package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;

@Repository
public class OrderYzwDaoImpl extends BaseDaoImpl<OrderYzw, String>  implements OrderYzwDao{

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public String find_last_id() {
		String sql = "select id from vorder order by sf_create_time desc limit 1";
		List<String> list = getSession().createNativeQuery(sql).list();
		if(list.size()>0)
			return list.get(0);
		else
			return "20000101001";
	}

	@Override
	public boolean isCustomerFirstOrder(OrderYzw order) {
		String hql = "select count(*) from OrderYzw where customer.id=:customerId and product.name like '%卡%' and payedDate <:payedDate";
		@SuppressWarnings("unchecked")
		List<Long> counts = (List<Long>) getHibernateTemplate().findByNamedParam(
				hql, 
				new String[]{"customerId", "payedDate"}, 
				new Object[]{order.getCustomer().getId(), order.getPayedDate()});
		if(counts.get(0) >0)
			return false;
		else
			return true;
	}

	@Override
	public float get_effective_brockerage_base(OrderYzw order) {
		// TODO Auto-generated method stub
		return 0;
	}



}
