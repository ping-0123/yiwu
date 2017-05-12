package com.test;

import java.util.Calendar;
import java.util.Date;

import javax.enterprise.inject.New;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class CalendarTest {

	@Test
	public void testDateCompare(){
		Date today = new Date();
		Date past = new Date(1989, 12,27);
		
		Calendar t = Calendar.getInstance();
		Calendar p = Calendar.getInstance();
		p.setTime(past);
		t.add(Calendar.YEAR, -18);
		System.out.println(t.getTime());
		System.out.println(t.compareTo(p));
	}

	
	@Test
	public void testDate(){
		Date date = new Date();
		date.setTime(1494577345000L);
		System.out.println(date);
	}
}
