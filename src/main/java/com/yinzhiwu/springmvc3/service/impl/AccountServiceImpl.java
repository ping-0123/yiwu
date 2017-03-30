package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.AccountDao;
import com.yinzhiwu.springmvc3.entity.Account;
import com.yinzhiwu.springmvc3.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao accountDao;

	@Override
	public void transfer(String out, String in, double balance) {
		accountDao.outBalance(out, balance);
		accountDao.inBalance(in, balance);
	}

	@Override
	public void show() {
		System.out.println("AccountServiceImpl show called");
	}

	@Override
	public void save(Account account) {
		accountDao.save(account);
	}
	
	
}
