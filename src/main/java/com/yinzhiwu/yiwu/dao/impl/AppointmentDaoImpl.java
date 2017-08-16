package com.yinzhiwu.yiwu.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.AppointmentDao;
import com.yinzhiwu.yiwu.entity.yzwOld.Appointment;
import com.yinzhiwu.yiwu.model.LessonOldApiView;

@Repository
public class AppointmentDaoImpl extends BaseDaoImpl<Appointment, Integer> implements AppointmentDao {

	@SuppressWarnings("unchecked")
	@Override
	public int getAppointedStudentCount(int lessonId) {
		String hql = "select count(1) from Appointment where coursehourId = :lessonId and status = '预约'";
		List<Long> list = (List<Long>) getHibernateTemplate().findByNamedParam(hql, "lessonId", lessonId);
		return (list.get(0)).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public LessonOldApiView.AttendedStatus findStatus(int lessonId, int customerId) {
		String hql = "select count(*) from Appointment where coursehourId = :lessonId and customerId = :customerId and status = '预约'";
		List<Long> list = (List<Long>) getHibernateTemplate().findByNamedParam(
				hql,
				new String[] { "lessonId", "customerId" }, 
				new Object[] { lessonId, customerId });
		return list.get(0).intValue() > 0 ? LessonOldApiView.AttendedStatus.APPONTED
				: LessonOldApiView.AttendedStatus.UN_APOINTED;
	}

	@Override
	public void saveOrUpdate(Appointment entity) {
		entity.setLastChangeTime(new Date());
		super.saveOrUpdate(entity);
	}

	@Override
	public void update(Appointment entity) {
		entity.setLastChangeTime(new Date());
		super.update(entity);
	}

}
