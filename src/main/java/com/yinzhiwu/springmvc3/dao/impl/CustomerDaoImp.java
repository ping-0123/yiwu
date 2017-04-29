package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.CustomerDao;
import com.yinzhiwu.springmvc3.entity.Customer;

@Repository
public class CustomerDaoImp extends BaseDaoImpl<Customer, Integer> implements CustomerDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findByProperty(String propertyName, Object value) {
		String hql = "from Customer where " + propertyName + " =:value";
		return (List<Customer>) getHibernateTemplate().findByNamedParam(hql, "value", value);
	}

	@Override
	public Customer findByWeChat(String weChatNo) {
		List<Customer> list = findByProperty("weChat", weChatNo);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
