package com.yinzhiwu.springmvc3.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.yinzhiwu.springmvc3.dao.AppointmentEventDao;
import com.yinzhiwu.springmvc3.dao.AppointmentYzwDao;
import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.LessonYzwDao;
import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.income.AbstractAppointmentEvent;
import com.yinzhiwu.springmvc3.entity.income.AppointmentEvent;
import com.yinzhiwu.springmvc3.entity.income.UnAppointmentEvent;
import com.yinzhiwu.springmvc3.entity.yzw.AppointmentYzw.AppointStatus;
import com.yinzhiwu.springmvc3.entity.yzw.AppointmentYzw;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.entity.yzwOld.Appointment;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.service.AppointmentEventService;
import com.yinzhiwu.springmvc3.service.IncomeEventService;

import io.swagger.annotations.ApiParam;

@Service
public class AppointmentEventServiceImpl extends BaseServiceImpl<AbstractAppointmentEvent,Integer> implements AppointmentEventService {

	@Autowired public void setBaseDao(AppointmentEventDao appointmentEventDao){
		super.setBaseDao(appointmentEventDao);
	}
	@Autowired private DistributerDao distributerDao;
	@Autowired private IncomeEventService incomeEventService;
	@Autowired private LessonYzwDao lessonDao;
	@Autowired private AppointmentYzwDao appointmentDao;
	@Autowired private OrderYzwDao orderDao;
	
	/**
	 * 调用该函数前，先判断是否满足预约， 取消预约条件
	 */
	@Override
	public Integer save(AbstractAppointmentEvent event){
		Assert.notNull(event);
		Assert.notNull(event.getDistributer());
		Assert.notNull(event.getType());
		Assert.notNull(event.getLesson());
		
		return incomeEventService.save(event);
	}
	
	public void saveAppoint(int distributerId, int lessonId) throws Exception{
		Distributer distributer = distributerDao.get(distributerId);
		LessonYzw lesson = lessonDao.get(lessonId);
		
		CustomerYzw customer = distributer.getCustomer();
		if(isAppointed(customer, lesson))
			throw new Exception("您已预约课程：" + lesson.getName() + "无须重复预约");
		if(!isAppointable(customer, lesson))
			throw new Exception("您不能预约课程" + lesson.getName() + "请购买音之舞\"" + lesson.getSubCourseType() +"\"类舞蹈卡");
		AppointmentYzw appoint = new AppointmentYzw(lesson,customer);
		appointmentDao.save(appoint);
		AppointmentEvent event = new AppointmentEvent(distributer, 1f, lesson);
		save(event);
	}
	

	public void saveUnAppoint(int distributerId, int lessonId) throws Exception {
		Distributer distributer = distributerDao.get(distributerId);
		LessonYzw lesson = lessonDao.get(lessonId);
		CustomerYzw customer = distributer.getCustomer();
		AppointmentYzw appointment = appointmentDao.findAppointed(customer, lesson, AppointStatus.APPONTED);
		if(appointment == null) throw new Exception("您尚未预约课程\"" + lesson.getName() + "\", 不能做取消操作");
		//判断是否可以取消预约
		Calendar lessonStartTime = Calendar.getInstance();
		lessonStartTime.setTimeInMillis(lesson.getLessonDate().getTime() + lesson.getStartTime().getTime());
		Calendar currentTime = Calendar.getInstance();
		currentTime.add(Calendar.HOUR, 2);	
		if(currentTime.after(lessonStartTime)){
			throw new Exception("开课前两小时内不能取消预约");
		}
		appointment.setStatus(AppointStatus.UN_APOINTED);
		appointmentDao.update(appointment);
		UnAppointmentEvent event = new UnAppointmentEvent(distributer, 1f, lesson);
		save(event);
	}

	@SuppressWarnings("unused")
	private AppointStatus getStatus(CustomerYzw customer, LessonYzw lesson) {
		return appointmentDao.getAppointmentStatusByCustomerAndLesson(customer, lesson);			
	}
	
	private boolean isAppointed(CustomerYzw customer, LessonYzw lesson){
		return appointmentDao.isAppointed(customer, lesson);
	}

	private boolean isAppointable(CustomerYzw customer, LessonYzw lesson){
		String contract = orderDao.find_valid_contract_by_customer_by_subCourseType(customer.getId(), lesson.getSubCourseType());
		return (StringUtils.hasLength(contract))?true:false;
	}
	
}
