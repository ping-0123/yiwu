package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.service.FileService;

/**
*@Author ping
*@Time  创建时间:2017年10月21日下午1:51:30
*
*/

public class QiniuServiceTest extends BaseSpringTest{
	
	@Qualifier("qiniuServiceImpl")
	@Autowired private FileService qiniuService;
	
	@Test
	public void testDelete(){
		String key  = "FhfRyu6mSHa7vaz65whpN7gftyCA";
		if(qiniuService.delete(key))
			System.out.println("删除成功!");
	}
}
