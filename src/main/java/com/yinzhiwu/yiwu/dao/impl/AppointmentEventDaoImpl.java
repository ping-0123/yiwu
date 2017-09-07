package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.AppointmentEventDao;
import com.yinzhiwu.yiwu.entity.income.AbstractAppointmentEvent;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.model.view.AppointSuccessApiView;

@Repository
public class AppointmentEventDaoImpl extends BaseDaoImpl<AbstractAppointmentEvent, Integer>
		implements AppointmentEventDao {

	@Override
	public AppointSuccessApiView findAppointSuccessApiViewById(int eventId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT new " + AppointSuccessApiView.class.getName());
		hql.append("(");
		hql.append("t1.param");
		hql.append(",t6.incomeValue");
		hql.append(",t3.store.officialAddress.city");
		hql.append(",t3.store.name");
		hql.append(",t3.name");
		hql.append(",t4.danceDesc");
		hql.append(",t3.subCourseType");
		hql.append(",t3.dueTeacherName");
		hql.append(",t3.lessonDate");
		hql.append(",t3.startTime");
		hql.append(",t3.endTime");
		hql.append(",t7.contractNo");
		hql.append(",t7.start");
		hql.append(",t7.end");
		hql.append(",DATEDIFF(t7.end, CURDATE())");
		hql.append(",t7.validityTimes");
		hql.append(",t7.remainTimes");
		hql.append(",t7.withHoldTimes");
		hql.append(")");
		
		hql.append(" FROM AbstractAppointmentEvent t1");
		hql.append(" JOIN t1.lesson t3");
		hql.append(" LEFT JOIN t3.course t4");
		hql.append(" LEFT JOIN AppointmentYzw t2 WITH t2.distributer.id = t1.distributer.id AND t1.lesson.id=t2.lesson.id");
		hql.append(" LEFT JOIN OrderYzw t5 WITH t2.contractNo=t5.contract.contractNo" );
		hql.append(" JOIN t5.contract t7");
		hql.append(" LEFT JOIN IncomeRecord t6 WITH t6.incomeEvent.id = t1.id "
					+ "AND t6.benificiary.id = t1.distributer.id");
		
		hql.append(" WHERE t1.id=:Id");
		hql.append(" AND t6.incomeType.id =:incomeTypeId");
		hql.append(" ORDER BY t5.createTime DESC");
		
		List<AppointSuccessApiView> views = getSession().createQuery(hql.toString(), AppointSuccessApiView.class)
				.setParameter("incomeTypeId", IncomeType.EXP.getId())
				.setParameter("Id", eventId)
				.setMaxResults(1)
				.getResultList();
		
		if(views.size()> 0)
			return views.get(0);
		else{
			logger.error("不能找到Id为: " + eventId + " 的预约事件");
			return null;
		}
	}

}
