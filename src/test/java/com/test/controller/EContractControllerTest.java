package com.test.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.entity.yzw.ElectricContractYzw;
import com.yinzhiwu.yiwu.service.ElectricContractYzwService;

/**
*@Author ping
*@Time  创建时间:2017年8月17日下午1:48:10
*
*/

public class EContractControllerTest extends BaseSpringTest {
	
	@Autowired private ElectricContractYzwService econtractService;
	
	@Test
	public void testGet(){
		ElectricContractYzw electricContractYzw = econtractService.get("YZW20170617444");
		System.err.println(electricContractYzw.getContractType().getContent());
	}
}
