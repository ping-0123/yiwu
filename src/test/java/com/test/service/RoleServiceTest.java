package com.test.service;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.test.BaseSpringTest;

/**
*@Author ping
*@Time  创建时间:2017年9月15日下午8:25:43
*
*/

public class RoleServiceTest extends BaseSpringTest {

	
	@Test
	@Rollback(value=false)
	public void addSomeResource(){
		System.out.println("aaaa..........");
	}
}
