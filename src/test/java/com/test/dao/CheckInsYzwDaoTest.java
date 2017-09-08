package com.test.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.test.TestBase;
import com.yinzhiwu.yiwu.dao.CheckInsYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.util.CalendarUtil;

/**
*@Author ping
*@Time  创建时间:2017年9月8日下午5:29:55
*
*/

public class CheckInsYzwDaoTest extends TestBase {

	@Autowired
	private CheckInsYzwDao checkInsDao;
	
	@Test
	public void testFindSumHoursOfCheckedLessons(){
		String contractNo = "YZW20170812064";
		float hours = checkInsDao.findSumHoursOfCheckedLessonsByContractNo(contractNo);
		System.out.println(hours);
	}
	
	@Test
	public void testFindCheckedInLessonsByDate(){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -13);
		Date start = CalendarUtil.getDayBegin(calendar).getTime();
		System.out.println(start);
		Date end = CalendarUtil.getDayEnd(calendar).getTime();
		System.out.println(end);
		List<LessonYzw> lessons = checkInsDao.findCheckedInlessonsByDate(start, end);
		System.err.println(lessons.size());
		Gson gson = new Gson();
		try{
			System.err.println(gson.toJson(lessons));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
