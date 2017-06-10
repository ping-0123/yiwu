package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.enums.Gender;
import com.yinzhiwu.springmvc3.service.DistributerService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DistributerServiceTest {

	@Autowired
	@Qualifier(value="distributerServiceImplTwo")
	private DistributerService distributerService;
	
	@Test
	public void saveRegister(){
		Distributer d = new Distributer();
		d.setPhoneNo("18803332928");
		d.setPassword("suning0987");
		d.setGender(Gender.FEMALE);
		d.setWechatNo("wechatNo004");
		
		String invatationCode = "5onx529d";
		distributerService.register(invatationCode, d);
	}
}
