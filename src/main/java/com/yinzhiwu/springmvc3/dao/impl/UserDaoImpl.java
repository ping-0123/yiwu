package com.yinzhiwu.springmvc3.dao.impl;

import com.yinzhiwu.springmvc3.dao.UserDao;

//@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public void show() {
		System.out.println("UserDaoImpl called");
	}

}
