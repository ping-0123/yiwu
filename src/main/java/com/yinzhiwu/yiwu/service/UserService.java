package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.Account;

public interface UserService {
	public Account findById(int id);
	public void show();
}
