package com.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.inject.New;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class CalendarTest {
	
	private static long MILLISESONDS_OF_ONE_DAY =1000*3600*24;
	
	private static DateFormat DATE_FOMATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Test
	public void test(){
		System.out.println("aa\nbb\n");
	}

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
	
	@Test
	public void truncateDate(){
		Date date = new Date();
		System.out.println(MILLISESONDS_OF_ONE_DAY);
		System.out.println(DATE_FOMATE.format(date));
		System.out.println(date.getTime()/MILLISESONDS_OF_ONE_DAY);
		System.out.println(date.getTime());
		System.out.println((date.getTime()/MILLISESONDS_OF_ONE_DAY)*MILLISESONDS_OF_ONE_DAY);
		Date date1 = new Date((date.getTime()/MILLISESONDS_OF_ONE_DAY)*MILLISESONDS_OF_ONE_DAY);
		System.out.println(DATE_FOMATE.format(date1));
		System.out.println(DATE_FOMATE.format(new Date(1495162181191L)));
		System.out.println(new Date(2017,5,19).getTime());
		System.out.println(DATE_FOMATE.format(new Date(2017,5,19)));
	}
	
	@Test 
	public void truncateDate2(){
		Calendar calendar = Calendar.getInstance();
		System.out.println("calendar date..." + calendar.getTimeInMillis());
		Date date = new Date();
		System.out.println("new Date..." + date.getTime());
		System.out.println("system time..." + System.currentTimeMillis());
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND, 0);
		System.out.println("calendar after set..." + calendar.getTimeInMillis());
		System.out.println("calendar formate..." + DATE_FOMATE.format(calendar.getTime()));
		long l = calendar.getTimeInMillis()/MILLISESONDS_OF_ONE_DAY*MILLISESONDS_OF_ONE_DAY;
		System.out.println("calendar after div..." + l);
		System.out.println(new Date(l));
		long offset = calendar.getTimeZone().getRawOffset();
		System.out.println(offset/1000/3600);
	}
	
}
