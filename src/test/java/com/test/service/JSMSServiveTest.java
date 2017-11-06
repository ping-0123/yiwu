package com.test.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.service.JSMSService;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月7日 上午12:59:29
*
*/

public class JSMSServiveTest extends BaseSpringTest {
	
	@Autowired JSMSService jsmsService;
	
	@Test
	public void testSendPayWithdrawMessage(){
		System.err.println("start send message.");
		jsmsService.sendPayWithdrawMessage("1805878216", new Date(), 100f);
	}
	
	@Test
	public void testSendRegisterMessage(){
		jsmsService.sendRegisterMessage("18058782160");
	}
	
	@Test
	public void testValidateRegisterSMSCode(){
		if(jsmsService.validateRegisterSMSCode("18058782160", "186267"))
			logger.info("验证成功");
	}
}
