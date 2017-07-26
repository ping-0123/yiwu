package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.AccountDao;
import com.yinzhiwu.yiwu.dao.EmployeeDao;
import com.yinzhiwu.yiwu.entity.Account;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.EmployeeApiView;
import com.yinzhiwu.yiwu.service.AccountService;

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
		YiwuJson<EmployeeApiView> json = new YiwuJson<>();
		try {
			Account account = accountDao.login(accountName, password);
			EmployeeApiView e = new EmployeeApiView(employeeDao.get(account.getEmployee().getId()));
			json.setData(e);
		} catch (DataNotFoundException e) {
			json.setMsg(e.getMessage());
			json.setResult(false);
		}

		return json;
	}

}
