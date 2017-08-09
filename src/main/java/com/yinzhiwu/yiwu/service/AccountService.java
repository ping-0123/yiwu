package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.Account;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.EmployeeApiView;

public interface AccountService {

	public void transfer(String out, String in, double balance);

	public void show();

	public void save(Account account);

	public YiwuJson<EmployeeApiView> login(String account, String password);
}
