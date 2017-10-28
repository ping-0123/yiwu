package com.yinzhiwu.yiwu.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.LessonAppointmentYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.business.LessonAppointmentException;
import com.yinzhiwu.yiwu.service.LessonAppointmentYzwService;

import io.jsonwebtoken.lang.Assert;

/**
*@Author ping
*@Time  创建时间:2017年10月28日下午2:00:50
*
*/

@Service
public class LessonAppointmentYzwServiceImpl extends BaseServiceImpl<LessonAppointmentYzw,Integer> implements LessonAppointmentYzwService {

	@Autowired
	public void setBaseDao(LessonAppointmentYzwDao dao){super.setBaseDao(dao);}
	@Autowired private LessonAppointmentYzwDao lessonAppointmentDao;
	@Autowired private ApplicationContext applicationContext;
	@Autowired private OrderYzwDao orderDao;
	
	@Override
	public LessonAppointmentYzw doAppoint(Distributer distributer, LessonYzw lesson) 
			throws LessonAppointmentException, DataNotFoundException
	{
		Assert.notNull(distributer);
		Assert.notNull(lesson);
		
		//判断是否可预约
		if(CourseType.CLOSED == lesson.getCourseType())
			throw new LessonAppointmentException("封闭式课不需要预约");
		
		Calendar twoHoursBeforeLessonStart = Calendar.getInstance();
		twoHoursBeforeLessonStart.setTime(lesson.getStartDateTime());
		twoHoursBeforeLessonStart.add(Calendar.HOUR_OF_DAY, -2);
		if(new Date().after(twoHoursBeforeLessonStart.getTime()))
			throw new LessonAppointmentException("请提前2小时预约");
		
		//保存预约
		Contract contract = orderDao.findCheckableContractOfCustomerAndLesson(distributer.getCustomer(), lesson);
		LessonAppointmentYzw appointment = new LessonAppointmentYzw(lesson,distributer,contract.getContractNo());
		lessonAppointmentDao.save(appointment);
		
		//推送事件
		applicationContext.publishEvent(appointment);
		
		return appointment;
	}
}
	