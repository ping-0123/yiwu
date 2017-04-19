package com.yinzhiwu.springmvc3.dao;

import org.hibernate.exception.DataNotFoundException;

import com.yinzhiwu.springmvc3.entity.Account;

public interface AccountDao extends IBaseDao<Account, Integer>{

	public Account login(String account, String password) throws DataNotFoundException;

	
}
