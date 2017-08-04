package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.AccountDao;
import com.yinzhiwu.yiwu.entity.Account;
import com.yinzhiwu.yiwu.service.UserService;

@Service
public class UserServiceImplThree implements UserService {

	@Autowired
	private AccountDao dao;

	public void show() {
		System.out.println("UserServiceImplThree  show called");
	}

	@Override
	public Account findById(int id) {
		return dao.get(id);
	}

}