package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.yiwu.enums.Gender;
import com.yinzhiwu.yiwu.model.DistributerRegisterModel;
import com.yinzhiwu.yiwu.service.DistributerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DistributerServiceTest {

	@Autowired
	private DistributerService distributerService;

	@Test
	public void testValueInjection() {
		System.err.println(distributerService.getHeadIconSavePath());
		System.err.println(distributerService.getHeadIconUrl());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void saveRegister() {
		String phonePre = "18803332";
		String wechatNoPre = "wechatNo";
		for (int i = 140; i < 150; i++) {
			DistributerRegisterModel d = new DistributerRegisterModel();
			d.setPhoneNo(phonePre + String.format("%03d", i));
			d.setPassword("suning0987");
			d.setGender(Gender.FEMALE);
			d.setWechatNo(wechatNoPre + String.format("%03d", i));

			String invatationCode = "e3inzouy";
			d.setInvitationCode(invatationCode);
			distributerService.register(d);
		}

	}
	
	@Test
	public void saveEmployeeRegister(){
		DistributerRegisterModel model = new DistributerRegisterModel();
		model.setPhoneNo("18258252477");
		model.setPassWord("suning0987");
		model.setGender(Gender.MALE);
		model.setWechatNo("wwwwwwwwwwwwww");
		model.setInvitationCode("e3it4owu");
		distributerService.register(model);
		
	}
}
