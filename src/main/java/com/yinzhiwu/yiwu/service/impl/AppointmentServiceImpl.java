package com.yinzhiwu.yiwu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.AppointmentDao;
import com.yinzhiwu.yiwu.dao.LessonDao;
import com.yinzhiwu.yiwu.dao.OrderDao;
import com.yinzhiwu.yiwu.entity.yzwOld.Appointment;
import com.yinzhiwu.yiwu.entity.yzwOld.Lesson;
import com.yinzhiwu.yiwu.entity.yzwOld.Order;
import com.yinzhiwu.yiwu.entity.yzwOld.Appointment.APPOINT_STATUS;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.AppointmentService;

/**
 * @see AppointmentEventServiceImpl
 * @author ping
 *
 */
@Deprecated
@Service
public class AppointmentServiceImpl implements AppointmentService {

	private static Log LOG = LogFactory.getLog(AppointmentServiceImpl.class);

	@Autowired
	private AppointmentDao appointmentDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private LessonDao lessonDao;

	@Override
	public boolean appoint(int customerId, int lessonId) {
		if (!isAppointable(customerId, lessonId) || getStatus(customerId, lessonId) == APPOINT_STATUS.APPONTED)
			return false;
		Appointment a = new Appointment();
		a.setCoursehourId(lessonId);
		a.setCustomerId(customerId);
		a.setStatus("预约");
		a.setCreateTime(new Date());
		appointmentDao.save(a);
		return true;
	}

	@Override
	public boolean unAppoint(int customerId, int lessonId) {
		Appointment appointment = getAppointed(customerId, lessonId);
		if (appointment == null)
			return false;
		appointment.setStatus("取消");
		appointmentDao.saveOrUpdate(appointment);
		return true;
	}

	@Override
	public APPOINT_STATUS getStatus(int customerId, int lessonId) {
		if (getAppointed(customerId, lessonId) == null)
			return APPOINT_STATUS.UN_APOINTED;
		return APPOINT_STATUS.APPONTED;
	}

	private Appointment getAppointed(int customerId, int lessonId) {
		List<Appointment> appointments = appointmentDao.findByProperties(
				new String[]{"customerId","lessonId","status"},
				new Object[]{customerId, lessonId, "预约"});
		if(appointments.size() > 0)
			return appointments.get(0);
		else {
			return null;
		}

	}

	private boolean isAppointable(int customerId, int lessonId) {
		try {
			Lesson l = lessonDao.get(lessonId);
			List<Order> orders = orderDao.findValidOrders(customerId, l.getSubCourseType());
			if (orders.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			LOG.warn(e.getMessage());
			return false;
		}
	}

}
