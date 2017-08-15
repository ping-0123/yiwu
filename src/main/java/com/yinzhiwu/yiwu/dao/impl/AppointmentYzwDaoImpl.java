package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.AppointmentYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.AppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.AppointmentYzw.AppointStatus;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;

@Repository
public class AppointmentYzwDaoImpl extends BaseDaoImpl<AppointmentYzw, Integer> implements AppointmentYzwDao {

	@Override
	public boolean isAppointed(CustomerYzw customer, LessonYzw lesson) {
		long count = findCountByProperties(
				new String[] { "lesson.id", "customer.id", "status" },
				new Object[] { lesson.getId(), customer.getId(), AppointStatus.APPONTED });
		return count > 0 ;
	}

	@Override
	public AppointStatus getAppointmentStatusByCustomerAndLesson(CustomerYzw customer, LessonYzw lesson) {
		if (isAppointed(customer, lesson))
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
		List<AppointmentYzw> appointments = findByProperties(
				new String[] { "customer.id", "lesson.id", "status" },
				new Object[] { customer.getId(), lesson.getId(), status });
		if(appointments.size() ==0 )
			return null;
		return appointments.get(0);
	}

	@Override
	public String getAppointedContractNo(Integer distributerId, Integer lessonId) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT t1.contractNo");
		hql.append(" FROM AppointmentYzw t1");
		hql.append(" WHERE t1.lesson.id = :lessonId");
		hql.append(" AND t1.distributer.id = :distributerId");
		hql.append(" AND t1.status =:status");
		
		return getSession().createQuery(hql.toString(), String.class)
				.setParameter("lessonId", lessonId)
				.setParameter("distributerId", distributerId)
				.setParameter("status", AppointStatus.APPONTED)
				.getSingleResult();
	
	}

}
