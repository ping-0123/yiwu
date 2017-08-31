package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.test.TestBase;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.OrderApiView;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.OrderYzwService;

/**
*@Author ping
*@Time  创建时间:2017年8月31日下午5:52:14
*
*/


public class OrderYzwServiceTest  extends TestBase{
	
	@Autowired private OrderYzwService orderService;
	@Autowired private DistributerService distributerService;
	
	@Test
	public void testfindPageOfOrderApiViewByDistributer(){
//		int customerId = 33897;
		int distributerId = 3002113;
		Distributer distributer = distributerService.get(distributerId);
		if(distributer == null)
			return ;
		YiwuJson<PageBean<OrderApiView>> json =
				orderService.findPageOfOrderApiViewByDistributer(distributer, 1, 10);
		String json2 = new Gson().toJson(json);
		System.out.println(json2);
	}
}
