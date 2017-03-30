package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.Account;

public interface AccountDao {
	
	public void update1(String name, double balance);
	public void inBalance(String in, double balance);
	public void outBalance(String oug, double balance);
	public int save(Account account);
	public Account findById(int id);
}
