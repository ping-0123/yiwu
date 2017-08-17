package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.TestBase;
import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.income.BreakAppointmentEvent;
import com.yinzhiwu.yiwu.entity.income.IncomeEvent;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.service.IncomeEventService;

/**
*@Author ping
*@Time  创建时间:2017年8月17日下午3:04:16
*
*/

public class IncomeEventServiceTest extends TestBase{
	
	@Autowired private IncomeEventService incomeEventService;
	@Autowired private DistributerDao distributerDao;
	@Autowired private LessonYzwDao lessonDao;
	
	@Test
	public void testSave(){
		Distributer distributer = distributerDao.get(3000116);
		LessonYzw lesson = lessonDao.get(172387);
		IncomeEvent event = new BreakAppointmentEvent(distributer, lesson);
		incomeEventService.save(event);
	}
}
