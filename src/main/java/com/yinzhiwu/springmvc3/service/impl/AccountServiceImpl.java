package com.yinzhiwu.springmvc3.service.impl;

import org.hibernate.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.AccountDao;
import com.yinzhiwu.springmvc3.dao.EmployeeDao;
import com.yinzhiwu.springmvc3.entity.Account;
import com.yinzhiwu.springmvc3.model.EmployeeApiView;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public void transfer(String out, String in, double balance) {
	}

	@Override
	public void show() {
	}

	@Override
	public void save(Account account) {
		accountDao.save(account);
	}

	@Override
	public YiwuJson<EmployeeApiView> login(String accountName, String password) {
		YiwuJson<EmployeeApiView>  json = new YiwuJson<>();
		try {
			Account account = accountDao.login(accountName,password);
			EmployeeApiView e = new EmployeeApiView(employeeDao.get(account.getEmployeeId()));
			json.setData(e);
		} catch (DataNotFoundException e) {
			json.setMsg(e.getMessage());
			json.setResult(false);
		}
		
		return json;
	}
	
	
}
