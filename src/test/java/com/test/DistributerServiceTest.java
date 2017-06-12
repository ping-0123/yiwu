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
		String phonePre = "18803332";
		String wechatNoPre = "wechatNo";
		for(int i=101; i<120; i++){
			Distributer d = new Distributer();
			d.setPhoneNo(phonePre + String.format("%03d",i));
			d.setPassword("suning0987");
			d.setGender(Gender.FEMALE);
			d.setWechatNo(wechatNoPre +  String.format("%03d",i));
			
			String invatationCode = "e3ibzofv";
			distributerService.register(invatationCode, d);
		}
		
	}
}
