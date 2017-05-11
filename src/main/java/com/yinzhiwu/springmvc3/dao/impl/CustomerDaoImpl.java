package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.CustomerDao;
import com.yinzhiwu.springmvc3.entity.Customer;

@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer, Integer> implements CustomerDao {
	
	private static Log LOG = LogFactory.getLog(CustomerDaoImpl.class);

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
