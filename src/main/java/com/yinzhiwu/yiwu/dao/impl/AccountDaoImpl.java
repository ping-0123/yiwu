package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.AccountDao;
import com.yinzhiwu.yiwu.entity.Account;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

@Repository
public class AccountDaoImpl extends BaseDaoImpl<Account, Integer> implements AccountDao {

	@SuppressWarnings("unchecked")
	@Override
	public Account login(String account, String password) throws DataNotFoundException {
		if (findCountByProperty("account", account) == 0) {
			throw new DataNotFoundException("account " + account + " is not registered");
		}

		String hql = "From Account t1 where t1.account=:account and t1.password = md5(:password)";

		List<Account> list = (List<Account>) getHibernateTemplate().findByNamedParam(hql,
				new String[] { "account", "password" }, new Object[] { account, password });

		if (list.size() == 0) {
			throw new DataNotFoundException("password is incorrect");
		}

		return list.get(0);
	}

}
