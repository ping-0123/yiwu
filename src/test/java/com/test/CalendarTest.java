package com.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.yinzhiwu.yiwu.util.CalendarUtil;

@RunWith(BlockJUnit4ClassRunner.class)
public class CalendarTest {

	private static long MILLISESONDS_OF_ONE_DAY = 1000 * 3600 * 24;

	private static DateFormat DATE_FOMATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Test
	public void testGetDayBegin() {
		Calendar calendar = Calendar.getInstance();
		System.out.println(CalendarUtil.getDayBegin(calendar).getTime());
	}

	@Test
	public void testTimeZone() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.set(2017, 6, 30, 12, 00);
		System.out.println(calendar.getTimeZone());
		System.out.println(calendar.getTime());
		System.out.println(calendar.getTimeInMillis());

		System.err.println();
		Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		System.out.println(calendar2.getTimeZone());
		System.out.println(calendar2.getTime());
		System.out.println(calendar2.getTimeInMillis());
	}

	@Test
	public void test() {
		System.out.println("aa\nbb\n");
	}

	@Test
	public void testDate() {
		Date date = new Date();
		date.setTime(1494577345000L);
		System.out.println(date);
	}

	@Test
	public void truncateDate2() {
		Calendar calendar = Calendar.getInstance();
		System.out.println("calendar date..." + calendar.getTimeInMillis());
		Date date = new Date();
		System.out.println("new Date..." + date.getTime());
		System.out.println("system time..." + System.currentTimeMillis());

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		System.out.println("calendar after set..." + calendar.getTimeInMillis());
		System.out.println("calendar formate..." + DATE_FOMATE.format(calendar.getTime()));
		long l = calendar.getTimeInMillis() / MILLISESONDS_OF_ONE_DAY * MILLISESONDS_OF_ONE_DAY;
		System.out.println("calendar after div..." + l);
		System.out.println(new Date(l));
		long offset = calendar.getTimeZone().getRawOffset();
		System.out.println(offset / 1000 / 3600);
	}

	@Test
	public void testString() {
		String aa = "select * from Lesson";
		int i = aa.toUpperCase().indexOf("FROM");
		System.out.println(i);
		System.out.println("select count(*) " + aa.substring(i));
	}

}
