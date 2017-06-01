package com.yinzhiwu.springmvc3.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.LessonDao;
import com.yinzhiwu.springmvc3.entity.Lesson;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

@Repository
public class LessonDaoImpl extends BaseDaoImpl<Lesson, Integer>
	implements LessonDao
{

	private static Log LOG = LogFactory.getLog(LessonDaoImpl.class);
	
	@Override
	public Lesson findById(int lessonId) throws DataNotFoundException {
//		return (Lesson) getHibernateTemplate().get(Lesson.class, lessonId);
		return get(lessonId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lesson> findLessonWeekList(int storeId, String courseType, String teacherName, String danceCatagory,
			Date startDate, Date endDate) {

			StringBuilder hql = new StringBuilder(""
					+ " FROM Lesson l"
					+ " WHERE lessonDate between :startDate and :endDate"
					+ " and courseType <> '私教课'");
			if (storeId > 0)
				hql.append(" and storeId =" +  storeId);
			if (courseType !="" && courseType !=null)
				hql.append(" and courseType = '" + courseType.replaceAll("\\s*", "") + "'");
			if(teacherName !="" && teacherName !=null)
				hql.append(" and dueTeacherName like '%" + teacherName.replaceAll("\\s*", "") + "%'");
			if(danceCatagory !="" && danceCatagory !=null)
				hql.append(" and lessonDesc like '%" + danceCatagory.replaceAll("\\s*", "") + "%'");
			
			hql.append(" order by lessonDate, startTime");
			
//			LOG.info(getHibernateTemplate().getSessionFactory().getCurrentSession().hashCode());
			return (List<Lesson>) getHibernateTemplate().findByNamedParam(
					hql.toString(), 
					new String[]{"startDate","endDate"}, 
					new Object[]{startDate,endDate});
	}
	

}
