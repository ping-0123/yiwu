package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.AppointmentYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.AppointmentYzw;
import com.yinzhiwu.springmvc3.entity.yzw.AppointmentYzw.AppointStatus;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

@Repository
public class AppointmentYzwDaoImpl extends BaseDaoImpl<AppointmentYzw, Integer> implements AppointmentYzwDao {

	@Override
	public boolean isAppointed(CustomerYzw customer, LessonYzw lesson) {
		int count =0;
		try {
			count = findCountByProperties(
					new String[]{"lesson.id", "customer.id", "status"}, 
					new Object[]{lesson.getId(), customer.getId(), AppointStatus.APPONTED});
		} catch (Exception e) {
			logger.error(e);
		}
		return count>0?true:false;
	}

	@Override
	public AppointStatus getAppointmentStatusByCustomerAndLesson(CustomerYzw customer, LessonYzw lesson) {
		if(isAppointed(customer, lesson))
			return AppointStatus.APPONTED;
		else {
			return AppointStatus.UN_APOINTED;
		}
	}

	@Override
	public boolean isAppointable(CustomerYzw customer, LessonYzw lesson) {
		return false;
	}

	@Override
	public AppointmentYzw findAppointed(CustomerYzw customer, LessonYzw lesson, AppointStatus status) {
		try {
			List<AppointmentYzw> appointments = findByProperties(
					new String[]{"customer.id", "lesson.id","status" }, 
					new Object[]{customer.getId(), lesson.getId(), status});
			return appointments.get(0);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

}
