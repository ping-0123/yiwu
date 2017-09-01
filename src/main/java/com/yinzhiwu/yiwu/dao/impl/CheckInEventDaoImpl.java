package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CheckInEventDao;
import com.yinzhiwu.yiwu.entity.income.CheckInEvent;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.model.view.CheckInSuccessApiView;

@Repository
public class CheckInEventDaoImpl extends BaseDaoImpl<CheckInEvent, Integer> implements CheckInEventDao {

	@Override
	public CheckInSuccessApiView findCheckInSuccessApiViewById(int eventId) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new " + CheckInSuccessApiView.class.getName());
		hql.append("(");
		hql.append("t1.param");
		hql.append(",t6.incomeValue");
		hql.append(",t3.store.city");
		hql.append(",t3.store.name");
		hql.append(",t3.name");
		hql.append(",t4.danceDesc");
		hql.append(",t3.courseType");
		hql.append(",t3.dueTeacherName");
		hql.append(",t3.lessonDate");
		hql.append(",t3.startTime");
		hql.append(",t3.endTime");
		hql.append(",t7.contractNo");
		hql.append(",t7.start");
		hql.append(",t7.end");
		hql.append(",t7.validityTimes");
		hql.append(",t7.remainTimes");
		hql.append(",t7.withHoldTimes");
		hql.append(")");
		
		hql.append(" FROM CheckInEvent t1");
		hql.append(" JOIN t1.checkIn t2");
		hql.append(" JOIN t2.lesson t3");
		hql.append(" LEFT JOIN t3.course t4");
		hql.append(" JOIN OrderYzw t5 WITH t2.contractNo=t5.contract.contractNo" );
		hql.append(" JOIN t5.contract t7");
		hql.append(" LEFT JOIN IncomeRecord t6 WITH t6.incomeEvent.id = t1.id "
					+ "AND t6.benificiary.id = t1.distributer.id");
		
		hql.append(" WHERE t1.id=:Id");
		hql.append(" AND t6.incomeType.id =:incomeTypeId");
		hql.append(" ORDER BY t5.createTime DESC");
		
		List<CheckInSuccessApiView> views = getSession().createQuery(hql.toString(), CheckInSuccessApiView.class)
				.setParameter("Id", eventId)
				.setParameter("incomeTypeId", IncomeType.EXP.getId())
				.setMaxResults(1)
				.getResultList();
		if(views.size()>0)
			return views.get(0);
		else{
			logger.error("不存在Id为 " + eventId + " 签到事件");
			return null;
		}
	}

	
}
