package com.yinzhiwu.springmvc3.util;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class HibernateUtil extends HibernateDaoSupport {
	
	@Autowired
	public void setHibernateSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
}
