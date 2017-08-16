package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.view.CheckInSuccessApiView;
import com.yinzhiwu.yiwu.service.CheckInsYzwService;

/**
*@Author ping
*@Time  创建时间:2017年8月15日下午10:10:55
*
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CheckInTest {

	@Autowired private CheckInsYzwService checkInsYzwService;
	
	@Test
	public void customerCheckInTest(){
		try {
			CheckInSuccessApiView view = checkInsYzwService.saveCustomerCheckIn(3000188, 581154);
			System.err.println("CheckInTest.customerCheckInTest..." + view.getLessonName());
		} catch (YiwuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
