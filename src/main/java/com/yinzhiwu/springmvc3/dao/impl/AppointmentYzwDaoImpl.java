package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.AppointmentYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.AppointmentYzw;
import com.yinzhiwu.springmvc3.entity.yzw.AppointmentYzw.AppointStatus;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;

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

}
