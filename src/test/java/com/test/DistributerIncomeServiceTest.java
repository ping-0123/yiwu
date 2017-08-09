package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.service.DistributerIncomeService;
import com.yinzhiwu.yiwu.service.IncomeRecordService;

/**
 * @Author ping
 * @Time 创建时间:2017年7月15日下午3:26:05
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DistributerIncomeServiceTest {

	@Autowired
	private DistributerIncomeService distributerIncomeService;
	@Autowired
	private IncomeRecordService incomeRecordService;

	@Test
	public void test() {
		IncomeRecord record = incomeRecordService.get(6000167);
		distributerIncomeService.update_by_record(record);
	}
}
