package com.yinzhiwu.yiwu.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.AppointmentYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.AppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.AppointmentYzw.AppointStatus;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.util.CalendarUtil;

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
		
		List<String> contractNos =  getSession().createQuery(hql.toString(), String.class)
				.setParameter("lessonId", lessonId)
				.setParameter("distributerId", distributerId)
				.setParameter("status", AppointStatus.APPONTED)
				.getResultList();
		if(contractNos.size()>0)
			return contractNos.get(0);
		return null;
	}

	@Override
	public List<AppointmentYzw> findLastDayAppointments() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date start = CalendarUtil.getDayBegin(calendar).getTime();
		Date end = CalendarUtil.getDayEnd(calendar).getTime();
		
		StringBuilder hql = new StringBuilder();
		hql.append("FROM AppointmentYzw t1");
		hql.append(" WHERE t1.lesson.lessonDate BETWEEN :start AND :end");
		hql.append(" AND t1.status = :status");
		hql.append(" AND t1.distributer.id IS NOT NULL");
		
		return getSession().createQuery(hql.toString(), AppointmentYzw.class)
				.setParameter("start", start)
				.setParameter("end", end)
				.setParameter("status", AppointStatus.APPONTED)
				.getResultList();
	}

	@Override
	public Long findCountByLessonIdByCustomerIdByAppointStatus(Integer id, Integer id2, AppointStatus apponted) {
		return findCountByProperties(
				new String[]{"lesson.id", "customer.id", "status"},
				new Object[]{id, id2, apponted});
	}

	@Override
	public Long findCountByLessonIdByAppointStatus(Integer id, AppointStatus apponted) {
		return findCountByProperties(new String[]{"lesson.id", "status"},
					new Object[]{id, apponted});
	}

}
