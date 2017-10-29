package com.yinzhiwu.yiwu.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.AppointmentEventDao;
import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.LessonAppointmentYzwDao;
import com.yinzhiwu.yiwu.dao.LessonCheckInYzwDao;
import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.income.AbstractAppointmentEvent;
import com.yinzhiwu.yiwu.entity.income.AppointmentEvent;
import com.yinzhiwu.yiwu.entity.income.BreakAppointmentEvent;
import com.yinzhiwu.yiwu.entity.income.UnAppointmentEvent;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw.AppointStatus;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.model.view.AppointSuccessApiView;
import com.yinzhiwu.yiwu.service.AppointmentEventService;
import com.yinzhiwu.yiwu.service.IncomeEventService;

@Service
public class AppointmentEventServiceImpl extends BaseServiceImpl<AbstractAppointmentEvent, Integer>
		implements AppointmentEventService {

	@Autowired
	public void setBaseDao(AppointmentEventDao appointmentEventDao) {
		super.setBaseDao(appointmentEventDao);
	}

	@Autowired
	private DistributerDao distributerDao;
	@Autowired
	private IncomeEventService incomeEventService;
	@Autowired
	private LessonYzwDao lessonDao;
	@Autowired
	private LessonAppointmentYzwDao appointmentDao;
	@Autowired 
	private AppointmentEventDao appointmentEventDao;
	@Autowired
	private OrderYzwDao orderDao;
	@Autowired 
	private LessonCheckInYzwDao checkInsDao;

	/**
	 * 调用该函数前，先判断是否满足预约， 取消预约条件
	 */
	@Override
	public Integer save(AbstractAppointmentEvent event) {
		Assert.notNull(event);
		Assert.notNull(event.getDistributer());
		Assert.notNull(event.getType());
		Assert.notNull(event.getLesson());

		return incomeEventService.save(event);
	}

	@Override
	public AppointSuccessApiView saveAppoint(int distributerId, int lessonId) throws Exception {
		
		Distributer distributer = distributerDao.get(distributerId);
		LessonYzw lesson = lessonDao.get(lessonId);
		CustomerYzw customer = distributer.getCustomer();

		if (isAppointed(customer, lesson))
			throw new Exception("您已预约课程：" + lesson.getName() + "无须重复预约");
		//仅开放式课程可以预约>
		if(CourseType.CLOSED == lesson.getCourseType()  )
			throw new Exception("封闭式课程无须预约");
		//判断上课时间是否已过
		if ((new Date()).after(lesson.getStartDateTime()))
			throw new Exception("上课时间已过， 不能预约");
		//判断是否已签到
		if(checkInsDao.isCheckedIn(customer.getMemberCard(), lesson.getId()))
			throw new Exception("您已签到");
		// 判断卡权益是否可以预约
		Contract contract = orderDao.findCheckableContractOfCustomerAndLesson(customer, lesson);
		
		LessonAppointmentYzw appoint = new LessonAppointmentYzw(lesson, distributer, contract.getContractNo());
		appointmentDao.save(appoint);
		orderDao.updateContractWithHoldTimes(contract.getContractNo(), 1);
		AppointmentEvent event = new AppointmentEvent(distributer,  lesson);
		this.save(event);
		// return
		return appointmentEventDao.findAppointSuccessApiViewById(event.getId());
	}

	@Override
	public AppointSuccessApiView saveUnAppoint(int distributerId, int lessonId) throws Exception {
		Distributer distributer = distributerDao.get(distributerId);
		LessonYzw lesson = lessonDao.get(lessonId);
		CustomerYzw customer = distributer.getCustomer();
		LessonAppointmentYzw appointment = appointmentDao.findAppointed(customer, lesson, AppointStatus.APPONTED);
		if (appointment == null)
			throw new Exception("您尚未预约课程\"" + lesson.getName() + "\", 不能做取消操作");
		// 判断是否可以取消预约
		Calendar start = Calendar.getInstance();
		start.setTime(lesson.getStartDateTime());
		start.add(Calendar.HOUR, -2);
		Calendar currentTime = Calendar.getInstance();
		if (currentTime.after(start)) {
			throw new Exception("开课前两小时内不能取消预约");
		}
		if(checkInsDao.isCheckedIn(customer.getMemberCard(), lesson.getId()))
			throw new Exception("您已签到");
		
		appointment.setStatus(AppointStatus.UN_APOINTED);
		appointmentDao.update(appointment);
		orderDao.updateContractWithHoldTimes(appointment.getContractNo(), -1);
		
		UnAppointmentEvent event = new UnAppointmentEvent(distributer, lesson);
		save(event);
		
		return appointmentEventDao.findAppointSuccessApiViewById(event.getId());
	}

	@SuppressWarnings("unused")
	private AppointStatus getStatus(CustomerYzw customer, LessonYzw lesson) {
		return appointmentDao.getAppointmentStatusByCustomerAndLesson(customer, lesson);
	}

	private boolean isAppointed(CustomerYzw customer, LessonYzw lesson) {
		return appointmentDao.isAppointed(customer, lesson);
	}

	
	@Override
	public void saveAllLastDayBreakAppointments(){
		List<LessonAppointmentYzw> appointments = appointmentDao.findLastDayAppointments();
		for (LessonAppointmentYzw appointment : appointments) {
			if(appointment.getDistributer()!=null 
					&& appointment.getLesson() !=null
					&& !checkInsDao.isCheckedIn(appointment.getDistributer().getId(), appointment.getLesson().getId()))
			{
				AbstractAppointmentEvent event = new BreakAppointmentEvent(appointment.getDistributer(), appointment.getLesson());
				incomeEventService.save(event);
			}
		}
		orderDao.cleanWithHoldTimes();
	}
	
	
}
