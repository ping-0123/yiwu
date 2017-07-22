package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.enums.Gender;
import com.yinzhiwu.yiwu.service.DistributerService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DistributerServiceTest {

	@Autowired
	private DistributerService distributerService;
	
	@Test
	public void saveRegister(){
		String phonePre = "18803332";
		String wechatNoPre = "wechatNo";
		for(int i=140; i<150; i++){
			Distributer d = new Distributer();
			d.setPhoneNo(phonePre + String.format("%03d",i));
			d.setPassword("suning0987");
			d.setGender(Gender.FEMALE);
			d.setWechatNo(wechatNoPre +  String.format("%03d",i));
			
			String invatationCode = "e3inzouy";
			distributerService.register(invatationCode, d);
		}
		
	}
}
