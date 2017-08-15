package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.dao.AppointmentYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.AppointmentYzw;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EContractTest {

	@Autowired private AppointmentYzwDao appointmentYzwDao;
	@Autowired private OrderYzwDao orderDao;
	
	@Transactional
	@Test
	public void testFindLastDayAppointments(){
		List<AppointmentYzw> appointmentYzws = appointmentYzwDao.findLastDayAppointments();
		System.err.println(appointmentYzws.size());
	}
	
	@Transactional
	@Test
	public void testCleanWithHoldTimes(){
		int count = orderDao.cleanWithHoldTimes();
		System.err.println(count);
	}
}
