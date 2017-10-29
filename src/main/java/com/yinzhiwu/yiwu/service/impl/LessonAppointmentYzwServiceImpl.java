package com.yinzhiwu.yiwu.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.LessonAppointmentYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw.AppointStatus;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.business.LessonAppointmentException;
import com.yinzhiwu.yiwu.exception.business.LessonInteractiveException;
import com.yinzhiwu.yiwu.service.LessonAppointmentYzwService;
import com.yinzhiwu.yiwu.service.LessonInteractiveService;

import io.jsonwebtoken.lang.Assert;

/**
*@Author ping
*@Time  创建时间:2017年10月28日下午2:00:50
*
*/

@Service
public class LessonAppointmentYzwServiceImpl extends BaseServiceImpl<LessonAppointmentYzw,Integer> implements LessonAppointmentYzwService {

	@Autowired private LessonAppointmentYzwDao lessonAppointmentDao;
	@Autowired private LessonInteractiveService lessonInteractiveService;
	@Autowired private ApplicationContext applicationContext;
	
	@Autowired
	public void setBaseDao(LessonAppointmentYzwDao dao){super.setBaseDao(dao);}
	
	@Override
	public LessonAppointmentYzw doAppoint(Distributer distributer, LessonYzw lesson) 
			throws LessonAppointmentException
	{
		Assert.notNull(distributer);
		Assert.notNull(lesson);
		
		//判断是否可预约
		if(CourseType.CLOSED == lesson.getCourseType())
			throw new LessonAppointmentException("封闭式课不需要预约");
		
		LessonInteractive interactive;
		try {
			interactive = lessonInteractiveService.ensureInteractive(lesson, distributer);
		} catch (LessonInteractiveException e) {
			throw new LessonAppointmentException(e.getCause().getMessage(),e);
		}
		if(interactive.getAppointed())
			throw new LessonAppointmentException("已预约,无须重复预约");
		if(interactive.getAppointed())
			throw new LessonAppointmentException("已签到,预约应在签到之前");
		
			//需提前两小时预约
		Calendar twoHoursBeforeLessonStart = Calendar.getInstance();
		twoHoursBeforeLessonStart.setTime(lesson.getStartDateTime());
		twoHoursBeforeLessonStart.add(Calendar.HOUR_OF_DAY, -2);
		if(new Date().after(twoHoursBeforeLessonStart.getTime()))
			throw new LessonAppointmentException("请提前2小时预约");
			//预约人数已满
		if(lesson.getAppointedStudentCount() >= lesson.getClassRoom().getMachineCode())
			throw new LessonAppointmentException("预约人数已满");
		
		//保存预约
		LessonAppointmentYzw appointment = new LessonAppointmentYzw(lesson,distributer,interactive.getContracNo());
		lessonAppointmentDao.save(appointment);
		
		//推送事件
		applicationContext.publishEvent(appointment);
		
		return appointment;
	}
	
	@Override
	public LessonAppointmentYzw cancelAppoint(Distributer distributer, LessonYzw lesson) throws LessonAppointmentException{
		
		//需提前两小时取消预约
		Calendar twoHoursBeforeLessonStart = Calendar.getInstance();
		twoHoursBeforeLessonStart.setTime(lesson.getStartDateTime());
		twoHoursBeforeLessonStart.add(Calendar.HOUR_OF_DAY, -2);
		if(new Date().after(twoHoursBeforeLessonStart.getTime()))
			throw new LessonAppointmentException("请提前2小时取消预约");
		
		//取消预约
		LessonAppointmentYzw appointment;
		try {
			 appointment = lessonAppointmentDao.findAppointedOne(distributer.getId(), lesson.getId());
		} catch (DataNotFoundException e) {
			throw new LessonAppointmentException("未预约");
		}
		appointment.setStatus(AppointStatus.UN_APOINTED);
		lessonAppointmentDao.update(appointment);
		
		//推送事件
		applicationContext.publishEvent(appointment);
		
		return appointment;
	}
	
	
}
	