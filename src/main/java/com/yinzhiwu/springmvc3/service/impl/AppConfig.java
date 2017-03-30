package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.context.annotation.Bean;

import com.yinzhiwu.springmvc3.service.UserService;

//@Configuration
public class AppConfig {
	
	@Bean(name = "myService", initMethod="start", destroyMethod="stop")
	public UserService userService(){
		return new UserServiceImplTwo();
	}
}
