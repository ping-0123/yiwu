package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.AccountDao;
import com.yinzhiwu.springmvc3.entity.Account;
import com.yinzhiwu.springmvc3.service.UserService;

@Service
public class UserServiceImplOne implements UserService {
	
	
//	public void afterPropertiesSet() throws Exception {
//		System.out.println("UserServiceImplOne  afterPropertiesSet " );
//	}
	
	@Autowired
	private AccountDao dao;

	public void show() {
		System.out.println("UserServiceImplOne called..." + this.hashCode());
//		userDao.show();
	}

//	public void destroy() throws Exception {
//		System.out.println("UserServiceImplOne   destroy ");
//	}
	
//	public void defaultInit(){
//		System.out.println("UserServiceImplOne  defaultInit");
//	}
//	
//	public void defaultDestroy(){
//		System.out.println("UserServiceImplOne  defaultDestroy");
//	}
//	
	
	public void start(){
		System.out.println("UserServiceImplOne start called......");
	}
	
	public void stop(){
		System.out.println("UserServiceImplOne stop called.......");
	}

	@Override
	public Account findById(int id) {
		return null;
	}
}
