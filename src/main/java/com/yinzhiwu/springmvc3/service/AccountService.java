package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.Account;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.EmployeeApiView;

public interface AccountService {
	
	public void transfer(String out, String in, double balance);
	public void show();
	public void save(Account account);
	public YiwuJson<EmployeeApiView> login(String account, String password);
}
