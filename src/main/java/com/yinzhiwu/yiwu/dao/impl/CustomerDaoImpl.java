package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CustomerDao;
import com.yinzhiwu.yiwu.entity.yzwOld.Customer;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer, Integer> implements CustomerDao {
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findByProperty(String propertyName, Object value) {
		String hql = "from Customer where " + propertyName + " =:value";
		return (List<Customer>) getHibernateTemplate().findByNamedParam(hql, "value", value);
	}

	@Override
	public Customer findByWeChat(String weChatNo) throws DataNotFoundException {
		List<Customer> list = findByProperty("weChat", weChatNo);
//		LOG.info("CustomerDaoImpl session " + getSession().hashCode());
		if(list.size() > 0)
			return list.get(0);
		else
			throw new DataNotFoundException(CustomerDaoImpl.class, "weChat", weChatNo);
	}

	@Override
	public Customer findByPhoneNo(String phoneNo) throws DataNotFoundException  {
		List<Customer> list = findByProperty("mobilePhone", phoneNo);
		if(list.size() > 0)
			return list.get(0);
		else
			throw new DataNotFoundException(CustomerDaoImpl.class, "mobilePhone", phoneNo);
	}

}