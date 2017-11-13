package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.JJWTService;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月6日 上午12:54:25
*
*/

public class JJWTServiceTest extends BaseSpringTest{
	
	@Autowired private DistributerService  distributerService;
	@Autowired private JJWTService jjwtService;
	
	@Test
	public void testJJwt(){
		Integer distributerId = 3000116;
		try {
			Distributer distributer = distributerService.get(distributerId);
			String token = jjwtService.createDistributerToken(distributer);
			System.err.println("token is ...." + token);
			Distributer distributer2 = jjwtService.parseDistributerToken(token);
			System.err.println("distributer2 is ....." + distributer2.getName());
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
