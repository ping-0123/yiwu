package com.yinzhiwu.springmvc3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.AccountDao;
import com.yinzhiwu.springmvc3.entity.Account;

@Service
public class UserServiceImplFour implements UserService {
	
	@Autowired
	private AccountDao dao;

	@Override
	public void show() {
		System.out.println("UserServiceImplFour show called.....");
//		throw new RuntimeException();
	}

	@Override
	public Account findById(int id) {
		return dao.findById(id);
	}

}
