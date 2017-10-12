package com.test.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.service.UserService;

/**
*@Author ping
*@Time  创建时间:2017年9月7日下午3:57:43
*
*/

public class UserServiceTest extends BaseSpringTest{

	@Autowired private UserService userService;
	
//	@Test
	public void testChangePassword(){
		int id = 331;
		try{
			userService.modifyPassword(id, "yiping");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
//	@Test
	@Rollback(value=false)
	public void initPassword(){
		List<User>  users = userService.findAll();
		for (User user : users) {
			userService.modifyPassword(user.getId(), "yzw123456");
		}
	}
	
	@Test
	public void initTest(){
		System.out.println("fuck the world!");
	}
}
