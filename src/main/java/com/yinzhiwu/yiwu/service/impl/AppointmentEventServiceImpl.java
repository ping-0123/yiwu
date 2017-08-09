package com.yinzhiwu.yiwu.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.AppointmentEventDao;
import com.yinzhiwu.yiwu.dao.AppointmentYzwDao;
import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.IncomeRecordDao;
import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.income.AbstractAppointmentEvent;
import com.yinzhiwu.yiwu.entity.income.AppointmentEvent;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.entity.income.UnAppointmentEvent;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.entity.yzw.AppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.AppointmentYzw.AppointStatus;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
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
	private AppointmentYzwDao appointmentDao;
	@Autowired
	private OrderYzwDao orderDao;
	@Autowired
	private IncomeRecordDao incomeRecordDao;

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
		//仅开放式课程可以预约
		if(CourseType.CLOSED == lesson.getCourseType()  )
			throw new Exception("封闭式课程无须预约");
		//判断上课时间是否已过
		if ((new Date()).after(lesson.getStartDateTime()))
			throw new Exception("上课时间已过， 不能预约");
		// 判断卡权益是否可以预约
		Contract contract = orderDao.find_valid_contract_by_customer_by_subCourseType(
				customer.getId(),
				lesson.getSubCourseType());
		//TODO 有漏洞, 客户预约后，剩余次数并没有减掉，而是要上完课签到之后第二天才能减掉  也就是说剩余次数为1可以预约很多次课.
		if (contract == null)
			throw new Exception("您不能预约课程\"" + lesson.getName() + "\"\n请购买音之舞\"" + lesson.getSubCourseType().getName() + "\"类舞蹈卡");
		
		
		AppointmentYzw appoint = new AppointmentYzw(lesson, customer);
		appointmentDao.save(appoint);
		AppointmentEvent event = new AppointmentEvent(distributer,  lesson);
		this.save(event);
		// return
		IncomeRecord record = incomeRecordDao.findExpProducedByEvent(event.getId(), IncomeType.EXP);
		return new AppointSuccessApiView(event, contract, record.getIncomeValue());
	}

	@Override
	public AppointSuccessApiView saveUnAppoint(int distributerId, int lessonId) throws Exception {
		Distributer distributer = distributerDao.get(distributerId);
		LessonYzw lesson = lessonDao.get(lessonId);
		CustomerYzw customer = distributer.getCustomer();
		AppointmentYzw appointment = appointmentDao.findAppointed(customer, lesson, AppointStatus.APPONTED);
		if (appointment == null)
			throw new Exception("您尚未预约课程\"" + lesson.getName() + "\", 不能做取消操作");
		// 判断是否可以取消预约
		Calendar currentTime = Calendar.getInstance();
		currentTime.add(Calendar.HOUR, 2);
		if (currentTime.after(lesson.getStartDateTime())) {
			throw new Exception("开课前两小时内不能取消预约");
		}
		appointment.setStatus(AppointStatus.UN_APOINTED);
		appointmentDao.update(appointment);
		UnAppointmentEvent event = new UnAppointmentEvent(distributer, 1f, lesson);
		save(event);

		IncomeRecord record = incomeRecordDao.findExpProducedByEvent(event.getId(), IncomeType.EXP);
		Contract contract = orderDao.find_valid_contract_by_customer_by_subCourseType(customer.getId(),
				lesson.getSubCourseType());
		if (contract != null) {
			return new AppointSuccessApiView(event, contract, record.getIncomeValue());
		}
		return null;
	}

	@SuppressWarnings("unused")
	private AppointStatus getStatus(CustomerYzw customer, LessonYzw lesson) {
		return appointmentDao.getAppointmentStatusByCustomerAndLesson(customer, lesson);
	}

	private boolean isAppointed(CustomerYzw customer, LessonYzw lesson) {
		return appointmentDao.isAppointed(customer, lesson);
	}

	/**
	 * 
	 * 
	 * @param customer
	 * @param lesson
	 * @return
	 */
	//TODO 存在一些问题 需要把当天已经预约的， 已经刷卡了的次数去掉
	private boolean isAppointable(CustomerYzw customer, LessonYzw lesson) {
		Contract contract = orderDao.find_valid_contract_by_customer_by_subCourseType(
				customer.getId(),
				lesson.getSubCourseType());
		return contract != null;
	}

}
