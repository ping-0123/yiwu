package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.Account;

public interface AccountService {
	
	public void transfer(String out, String in, double balance);
	public void show();
	public void save(Account account);
}
