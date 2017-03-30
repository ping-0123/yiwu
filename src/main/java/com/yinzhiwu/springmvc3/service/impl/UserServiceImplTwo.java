package com.yinzhiwu.springmvc3.service.impl;




import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.AccountDao;
import com.yinzhiwu.springmvc3.entity.Account;
import com.yinzhiwu.springmvc3.service.UserService;

//@Order(1)
//@Scope("prototype")
//@Component("main")
@Service("two")
public class UserServiceImplTwo implements 
							UserService , 
							InitializingBean, 
							DisposableBean,
							BeanNameAware{

	private String beanName;
	
	@Autowired
	private AccountDao dao;
	
	@Override
	public void show() {
		System.out.println("UserServiceImplTwo  show called" + this.hashCode());
	}
	
	public void start(){
//		System.out.println("UserServiceImplTwo start");
	}

	public void stop(){
//		System.out.println("UserServiceImplTwo stop");
	}
	
	public void defaulInit(){
		System.out.println("UserServiceImplTwo defaultInit");
	}
	
	public void defaultDestory(){
		System.out.println("UserServiceImplTwo defaultDestory" + beanName);
	}

	@Override
	public void destroy() throws Exception {
//		System.out.println("UserServiceImplTwo destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		System.out.println("UserServiceImplTwo afterPropertiesSet");
		
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
//		System.out.println("UserServiceImplTwo setBeanName " + this.beanName);
	}

	@Override
	public Account findById(int id) {
		return dao.findById(id);
	}
}
