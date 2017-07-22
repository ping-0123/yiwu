package com.yinzhiwu.yiwu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.AccountDao;
import com.yinzhiwu.yiwu.entity.Account;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

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
		try {
			return dao.get(id);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
