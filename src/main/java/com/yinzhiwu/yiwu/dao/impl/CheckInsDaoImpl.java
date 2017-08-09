package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CheckInsDao;
import com.yinzhiwu.yiwu.entity.yzwOld.CheckIns;

@Repository
public class CheckInsDaoImpl extends BaseDaoImpl<CheckIns, Integer> implements CheckInsDao {

	@SuppressWarnings("unchecked")
	@Override
	public int findCheckedInStudentCountByLessonId(String lessonId) {
		String hql = "select count(*) from CheckIns where lessonId = :lessonId"
				+ " and contract is not null and contract <> ''";
		List<Long> l = (List<Long>) getHibernateTemplate().findByNamedParam(hql, "lessonId", lessonId);
		return l.get(0).intValue();
	}
}
