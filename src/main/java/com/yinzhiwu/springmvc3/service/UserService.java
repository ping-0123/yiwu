package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.Account;

public interface UserService {
	public Account findById(int id);
	public void show();
}
