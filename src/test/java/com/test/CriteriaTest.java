package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.springmvc3.dao.IncomeRecordDao;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CriteriaTest {
	@Autowired private IncomeRecordDao incomeRecordDao;
	
	@Test
	public void test(){
		incomeRecordDao.getShareTweetRecordApiViews(3000050, null, null, null);
	}
}
