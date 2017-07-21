package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.dao.IncomeRecordDao;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.view.IncomeRecordApiView;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DynamicInstantiationTest {

	
	@Autowired private IncomeRecordDao incomeRecordDao;
	
	@Test
	public void test(){
		try {
			long start = System.currentTimeMillis();
			IncomeRecordApiView v2 = new IncomeRecordApiView(incomeRecordDao.get(6000061));
			long middle = System.currentTimeMillis();
			System.out.println("times of find view directly"  + ( middle - start));
			IncomeRecordApiView v = incomeRecordDao.findApiViewById(6000061);
			long end = System.currentTimeMillis();
			System.out.println("times of find view by inderectly" + (end -middle));
		} catch (DataNotFoundException e) {
			e.printStackTrace();
		}
	}
}
