package com.test.util;

import org.junit.Test;

import com.test.BaseTest;
import com.yinzhiwu.yiwu.service.UserService;
import com.yinzhiwu.yiwu.util.SpringUtils;

/**
*@Author ping
*@Time  创建时间:2017年9月19日下午12:09:43
*
*/

public class SpringUtilsTest  extends BaseTest{
	
	@Test
	public void test(){
		UserService bean = SpringUtils.getBean(UserService.class);
		System.err.println(bean.get(1));
	}
}
