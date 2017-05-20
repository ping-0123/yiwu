package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.entity.yzw.ElectricContractYzw;
import com.yinzhiwu.springmvc3.service.ElectricContractYzwService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EContractTest {
	
	@Autowired
	private ElectricContractYzwService electricContractYzwService;
	
	@Test
	public void test(){
		System.out.println("YZW20170410049");
//		ElectricContractYzw e = electricContractYzwService.get("YZW20170410049");
//		System.out.println(e.getContract().getContractNo());
	}
}
