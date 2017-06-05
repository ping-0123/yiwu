package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.AccountDao;
import com.yinzhiwu.springmvc3.entity.Account;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.service.UserService;

@Service
public class UserServiceImplThree 
	implements UserService
{

	@Autowired
	private AccountDao dao;
	public void show() {
		System.out.println("UserServiceImplThree  show called");
	}

	@Override
	public Account findById(int id) {
		try {
			return dao.get(id);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

}
