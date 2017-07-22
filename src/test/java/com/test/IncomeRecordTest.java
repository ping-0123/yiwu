package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.IncomeRecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IncomeRecordTest {
	
	@Autowired
	private IncomeRecordService service;
	
	@Test
	public void test(){
		int d = 15;
		int eventType= 10004;
		int relation = 10015;
		try {
			List<IncomeRecord> records  = service.findByProperties(
					new String[]{"contributor.id", "incomeEvent.type.id", "con_ben_relation.id"}, 
					new Object[]{d,eventType,relation});
			System.out.println(records.size());
		} catch (DataNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
