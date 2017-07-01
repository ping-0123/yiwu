package com.yinzhiwu.springmvc3.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.LessonDao;
import com.yinzhiwu.springmvc3.entity.yzwOld.Lesson;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

@Repository
public class LessonDaoImpl extends BaseDaoImpl<Lesson, Integer>
	implements LessonDao
{

	
	@Override
	public Lesson findById(int lessonId) throws DataNotFoundException {
//		return (Lesson) getHibernateTemplate().get(Lesson.class, lessonId);
		return get(lessonId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lesson> findLessonWeekList(int storeId, String courseType, String teacherName, String danceCatagory,
			Date startDate, Date endDate) throws DataNotFoundException {

			StringBuilder hql = new StringBuilder(""
					+ " FROM Lesson l"
					+ " WHERE lessonDate between :startDate and :endDate"
					+ " and courseType <> '私教课'");
			if (storeId > 0)
				hql.append(" and storeId =" +  storeId);
			if (courseType !=""  && courseType !=null && courseType != "全部")
				hql.append(" and courseType = '" + courseType.replaceAll("\\s*", "") + "'");
			if(teacherName !="" && teacherName !=null){
//				hql.append(" and (dueTeacherName like '%" + teacherName.replaceAll("\\s*", "") + "%'");
//				hql.append(" or actualTeacherName like '%" +  teacherName.replaceAll("\\s*", "") + "%')" );
				hql.append(" and (dueTeacherName ='" + teacherName + "'");
				hql.append(" or actualTeacherName ='" +  teacherName + "')" );
			}
			if(danceCatagory !="" && danceCatagory !=null)
				hql.append(" and lessonDesc like '%" + danceCatagory.replaceAll("\\s*", "") + "%'");
			
			hql.append(" order by lessonDate, startTime");
			
//			LOG.info(getHibernateTemplate().getSessionFactory().getCurrentSession().hashCode());
			List<Lesson> lessons = (List<Lesson>) getHibernateTemplate().findByNamedParam(
					hql.toString(), 
					new String[]{"startDate","endDate"}, 
					new Object[]{startDate,endDate});
			if(null==lessons || lessons.size()==0)
				throw new DataNotFoundException();
			return lessons;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findOrderInCourse(Lesson l) {
		Assert.notNull(l, "lesson 不能为空");
		
		logger.debug(l.getLessonId().toString() + "开始时间: " +  l.getStartDateTime());
		String hql = "SELECT COUNT(*) from Lesson WHERE lessonDate <= :lessonDate and courseid = :courseId";
		List<Long> longs = (List<Long>) getHibernateTemplate().findByNamedParam(hql,
				new String[]{"lessonDate", "courseId"}, 
				new Object[]{l.getLessonDate(), l.getCourseid()}) ;
		return longs.get(0).intValue();
	}



}
