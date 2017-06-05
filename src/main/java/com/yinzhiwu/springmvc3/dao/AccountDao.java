package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.Account;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

public interface AccountDao extends IBaseDao<Account, Integer>{

	public Account login(String account, String password) throws DataNotFoundException;

	
}
