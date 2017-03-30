package com.test;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_coursetable.xml")
public class TestCalendar {
	
	@Test
	public void testFirstDayOfWeek(){
		Calendar ca = Calendar.getInstance();
		ca.setFirstDayOfWeek(Calendar.SUNDAY);
		System.out.println(ca.get(Calendar.DAY_OF_WEEK));
		System.out.println(4/7);
	}
}
