package com.yinzhiwu.yiwu.service.impl;

import org.springframework.context.annotation.Bean;

import com.yinzhiwu.yiwu.service.UserService;

//@Configuration
public class AppConfig {

	@Bean(name = "myService", initMethod = "start", destroyMethod = "stop")
	public UserService userService() {
		return new UserServiceImplTwo();
	}
}
