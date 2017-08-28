package com.test.dao;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.TestBase;
import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.util.CalendarUtil;

/**
*@Author ping
*@Time  创建时间:2017年8月23日下午2:17:15
*
*/

public class OrderYzwDaoTest  extends TestBase{

	@Autowired
	private OrderYzwDao orderYzwDao;
	@Autowired private CustomerYzwDao customerDao;
	@Autowired private LessonYzwDao lessonDao;
	
	@Test
	public void testFindCheckableContractsOfCustomerAndLesson(){
		CustomerYzw customer = customerDao.get(33897);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 9);
		List<LessonYzw> lessons = lessonDao.findByProperties(
				new String[]{"store.id", "lessonDate", "courseType"}, 
				new Object[]{63, CalendarUtil.getDayBegin(calendar).getTime(), CourseType.OPENED});
		System.out.println("lessons count is " + lessons.size());
		for (LessonYzw lesson : lessons) {
			try {
				orderYzwDao.findCheckableContractOfCustomerAndLesson(customer, lesson);
				System.out.println("可以预约成功");
			} catch (DataNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testFindContractByContractNo(){
		String contractNo = "YZW20170822153";
		System.out.println(orderYzwDao.findContractByContractNo(contractNo));
	}
}
