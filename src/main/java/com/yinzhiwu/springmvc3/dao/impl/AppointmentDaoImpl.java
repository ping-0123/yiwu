package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.AppointmentDao;
import com.yinzhiwu.springmvc3.entity.Appointment;
import com.yinzhiwu.springmvc3.model.LessonApiView;


@Repository
public class AppointmentDaoImpl extends BaseDaoImpl<Appointment, Integer> implements AppointmentDao {

	@SuppressWarnings("unchecked")
	@Override
	public int getAppointedStudentCount(int lessonId) {
		String hql = "select count(*) from Appointment where courseHourId = :lessonId and status = '预约'";
		List<Long> list = (List<Long>) getHibernateTemplate().findByNamedParam(hql, "lessonId", lessonId);
		return (list.get(0)).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public LessonApiView.AttendedStatus findStatus(int lessonId, int customerId) {
		String hql = "select count(*) from Appointment where coursehourId = :lessonId and customerId = :customerId and status = '预约'";
		List<Long>  list = (List<Long>) getHibernateTemplate().findByNamedParam(
				hql, 
				new String[]{"lessonId", "customerId"}, 
				new Object[]{lessonId,customerId});
		return list.get(0).intValue()>0?  
				LessonApiView.AttendedStatus.APPONTED: 
				LessonApiView.AttendedStatus.UN_APOINTED;
	}

}
