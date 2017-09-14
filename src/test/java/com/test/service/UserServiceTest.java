package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.test.BaseTest;
import com.yinzhiwu.yiwu.service.UserService;

/**
*@Author ping
*@Time  创建时间:2017年9月7日下午3:57:43
*
*/

public class UserServiceTest extends BaseTest{

	@Autowired private UserService userService;
	
	@Test
	@Rollback(value=false)
	public void testChangePassword(){
		int id = 331;
		try{
			userService.modifyPassword(id, "yiping");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
