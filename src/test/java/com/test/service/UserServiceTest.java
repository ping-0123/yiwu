package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.test.TestBase;
import com.yinzhiwu.yiwu.service.UserService;

/**
*@Author ping
*@Time  创建时间:2017年9月7日下午3:57:43
*
*/

public class UserServiceTest extends TestBase{

	@Autowired private UserService userService;
	
	@Test
	@Rollback(value=false)
	public void testChangePassword(){
		int id = 1;
		try{
			userService.modifyPassword(id, "admin");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
