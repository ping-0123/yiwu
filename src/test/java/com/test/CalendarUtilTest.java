package com.test;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.yinzhiwu.yiwu.util.CalendarUtil;

/**
*@Author ping
*@Time  创建时间:2017年8月4日下午3:15:33
*
*/

@RunWith(BlockJUnit4ClassRunner.class)
public class CalendarUtilTest {
	
	@Test
	public void testGetAge(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,2001);
		System.out.println(calendar.getTime());
		System.out.println(CalendarUtil.getAge(calendar.getTime()));
	}
}
