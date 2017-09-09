package com.test.task;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.test.TestBase;
import com.yinzhiwu.yiwu.service.impl.TempTaskImpl;
import com.yinzhiwu.yiwu.util.CalendarUtil;

/**
*@Author ping
*@Time  创建时间:2017年9月9日下午4:40:35
*
*/

public class TempTaskTest extends TestBase {
	
	@Autowired private TempTaskImpl taskService;
	
	@Test
	@Rollback(value=false)
	public void testTemp(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		Date end = CalendarUtil.getDayEnd( calendar).getTime();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date start = CalendarUtil.getDayBegin(calendar).getTime();
		
		taskService.set_super_distributer_and_caculate_brokerage(start , end);
	}
	
	@Test
	public void testCalendar(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		Date end = CalendarUtil.getDayEnd( calendar).getTime();
		System.out.println(end);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date start = CalendarUtil.getDayBegin(calendar).getTime();
		System.out.println(start);
	}
}

