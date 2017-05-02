package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.springmvc3.entity.FundsRecordType;
import com.yinzhiwu.springmvc3.service.RecordTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_coursetable.xml")
public class TypeTest {

	@Autowired
	public RecordTypeService recordTypeService;
	
	@Test
	public void saveFundsRecordTypes(){
		FundsRecordType fundsRecordType = new FundsRecordType("注册", "一级客户注册获取50基金", 50F);
		recordTypeService.save(fundsRecordType);
	}
}
