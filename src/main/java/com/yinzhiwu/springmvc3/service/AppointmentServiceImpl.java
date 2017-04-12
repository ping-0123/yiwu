package com.yinzhiwu.springmvc3.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.AppointmentDao;
import com.yinzhiwu.springmvc3.entity.Appointment;
import com.yinzhiwu.springmvc3.entity.Appointment.APPOINT_STATUS;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	private AppointmentDao appointmentDao;

	@Override
	public boolean appoint(int customerId, int lessonId) {
		if(getStatus(customerId, lessonId) == APPOINT_STATUS.APPONTED)
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
		if(appointment == null)
			return false;
		appointment.setStatus("取消");
		appointmentDao.saveOrUpdate(appointment);
		return true;
	}

	@Override
	public APPOINT_STATUS getStatus(int customerId, int lessonId) {
		if(getAppointed(customerId, lessonId) == null)
			return APPOINT_STATUS.UN_APOINTED;
		return APPOINT_STATUS.APPONTED;			
	}
	
	
	private Appointment getAppointed(int customerId, int lessonId){
		Map<String, Object> map = new HashMap<>();
		map.put("customerId", customerId);
		map.put("lessonId", lessonId);
		map.put("status", "预约");
		List<Appointment> appointments = appointmentDao.findByProperties(map);
		if (appointments.size()==0 || appointments.size()>1)
			return null;
		return appointments.get(0);
	}

}
