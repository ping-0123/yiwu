package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.TestBase;
import com.yinzhiwu.yiwu.model.view.StoreApiView;
import com.yinzhiwu.yiwu.service.DistributerService;

/**
*@Author ping
*@Time  创建时间:2017年8月29日下午8:16:08
*
*/

public class DistributerServiceTest extends TestBase {
	
	@Autowired private DistributerService distributerService;
	
	@Test
	public void testFindDefaultStoreApiView(){
		int distributerId = 3001124;
		System.out.println(((StoreApiView)distributerService.findDefaultStoreApiView(distributerId).getData()).getName());
	}
}
