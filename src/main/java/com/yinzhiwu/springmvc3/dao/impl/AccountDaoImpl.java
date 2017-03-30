package com.yinzhiwu.springmvc3.dao.impl;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.AccountDao;
import com.yinzhiwu.springmvc3.entity.Account;
	
@Repository
public class AccountDaoImpl extends HibernateDaoSupport  implements AccountDao {

	@Autowired
	public void setHibernateSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	
	@Override
	public void update1(String name, double balance) {
		;
	}

	@Override
	public void inBalance(String in, double balance) {
		String hql = "update Account set balance = balance + ? where name = ?";
		getHibernateTemplate().bulkUpdate(hql, new Object[]{balance, in});
	}

	@Override
	public void outBalance(String out, double balance) {
		String hql = "update Account set balance = balance - ? where name = ?";
		getHibernateTemplate().bulkUpdate(hql, new Object[]{balance, out});
	}

	@Override
	public int save(Account account) {
		Assert.notNull(account,"account is requried");
		return (int) getHibernateTemplate().save(account);
		
	}


	@Override
	public Account findById(int id) {
		return getHibernateTemplate().get(Account.class, 1);
	}


}
