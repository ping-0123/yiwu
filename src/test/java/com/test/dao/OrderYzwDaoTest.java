package com.test.dao;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.test.TestBase;
import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.OrderApiView;
import com.yinzhiwu.yiwu.util.CalendarUtil;
import com.yinzhiwu.yiwu.util.ReflectUtils;

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
	
	@Transactional
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
	
	@Test
	public void testfindPageOfOrderApiViewByCustomerId(){
		int customerId = 33897;
		
		PageBean<OrderApiView> page = orderYzwDao.findPageOfOrderApiViewByCustomerId(customerId, 1, 10);
		List<OrderApiView> views = page.getData();
		Field[] fields = ReflectUtils.getAllFields(OrderApiView.class);
		for (OrderApiView view : views) {
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					System.err.println(views.indexOf(view)+ " " + field.getName() + " : " + field.get(view));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.err.println("\n");
		}
	}
}
