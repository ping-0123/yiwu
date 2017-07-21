package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.Account;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface AccountDao extends IBaseDao<Account, Integer>{

	public Account login(String account, String password) throws DataNotFoundException;

	
}
