package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.model.view.StoreApiView;
import com.yinzhiwu.yiwu.service.DistributerService;

/**
*@Author ping
*@Time  创建时间:2017年8月29日下午8:16:08
*
*/

public class DistributerServiceTest extends BaseSpringTest {
	
	@Autowired private DistributerService distributerService;
	
	@Test
	public void testFindDefaultStoreApiView(){
		try {
			
			int distributerId = 3001124;
			Distributer distributer = distributerService.get(distributerId);
			System.out.println(((StoreApiView)distributerService.findDefaultStoreApiView(distributerId).getData()).getName());
		} catch (Exception e) {
			if(e instanceof NullPointerException)
				System.err.println(e.getMessage());
			else {
				logger.error(e);
			}
		
		}
	}
}
