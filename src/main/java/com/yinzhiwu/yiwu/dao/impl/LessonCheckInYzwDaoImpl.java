package com.yinzhiwu.yiwu.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.LessonCheckInYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;

@Repository
public class LessonCheckInYzwDaoImpl extends BaseDaoImpl<LessonCheckInYzw, Integer> implements LessonCheckInYzwDao {


	@Override
	public int findCountByCustomerId(int customerId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT COUNT(1)");
		hql.append(" FROM LessonCheckInYzw t1");
		hql.append(" JOIN CustomerYzw t2 ON (t1.memberCard = t2.memberCard)");
		hql.append(" WHERE t2.id =:customerId");
		
		return getSession().createQuery(hql.toString(),Long.class)
				.setParameter("customerId", customerId)
				.getSingleResult()
				.intValue();
	}


	@Override
	public PageBean<LessonApiView> findPageByContractNos(List<String> contractNos, int pageNo, int pageSize) {
		if (contractNos == null || contractNos.size() == 0)
			return null;
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<LessonApiView> criteria = builder.createQuery(LessonApiView.class);
		Root<?> checkIn = criteria.from(LessonCheckInYzw.class);
		Join<LessonCheckInYzw, LessonYzw> lessonJoin = checkIn.join("lesson", JoinType.LEFT);
		Join<LessonYzw, CourseYzw> courseJoin = lessonJoin.join("course", JoinType.LEFT);
		// Path<LessonYzw> lesson = checkIn.get("lesson");
		criteria.select(builder.construct(LessonApiView.class,
				lessonJoin.get("id"), 
				lessonJoin.get("name"),
				courseJoin.get("id"), 
				courseJoin.get("danceDesc"), 
				courseJoin.get("danceGrade"),
				lessonJoin.get("lessonDate"), 
				lessonJoin.get("startTime"), 
				lessonJoin.get("endTime"),
				lessonJoin.get("storeName"), 
				lessonJoin.get("dueTeacherName"))
				);
		javax.persistence.criteria.Predicate condition = checkIn.get("contractNo").in(contractNos);
		criteria.where(condition);
		criteria.orderBy(builder.desc(lessonJoin.get("lessonDate")), builder.desc(lessonJoin.get("startTime")));

		// 找出记录总数量
		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<LessonCheckInYzw> countRoot = countCriteria.from(LessonCheckInYzw.class);
		countCriteria.select(builder.count(countRoot));
		Predicate countPredicate = countRoot.get("contractNo").in(contractNos);
		countCriteria.where(countPredicate);
		Long totalCount = getSession().createQuery(countCriteria).getSingleResult();

		return findPageByCriteria(criteria, pageNo, pageSize, totalCount.intValue());
	}

	@Override
	public boolean isCheckedIn(CustomerYzw customer, LessonYzw lesson) {
		Assert.notNull(customer);
		Assert.notNull(lesson);

		StringBuilder hql = new StringBuilder();
		hql.append("SELECT count(1)");
		hql.append(" FROM LessonCheckInYzw t1");
		hql.append(" WHERE t1.lesson.id=:lessonId");
		hql.append(" AND t1.contractNo IN");
		hql.append(
				"(select t1.contract.contractNo from OrderYzw t1 where t1.contract.status='已审核' and t1.contract.subType=:subCourseType");
		hql.append(" and t1.contract.remainTimes>=1 and t1.contract.end >= :currdate)");
		@SuppressWarnings("unchecked")
		List<Long> counts = (List<Long>) getHibernateTemplate().findByNamedParam(hql.toString(),
				new String[] { "lessonId", "subCourseType", "currdate" },
				new Object[] { lesson.getId(), lesson.getSubCourseType(), new Date() });
		if (counts.get(0) > 0)
			return true;
		return false;
	}

	@Override
	public Date findCheckInTimeByProperties(Integer lessonId, Integer actualTeacherId) {
		
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT t1.createTime");
		hql.append(" FROM LessonCheckInYzw t1");
		hql.append(" WHERE t1.lesson.id =:lessonId");
		hql.append(" AND t1.teacher.id =:teacherId");
		return  getSession().createQuery(hql.toString(), Date.class)
				.setParameter("lessonId", lessonId)
				.setParameter("teacherId", actualTeacherId)
				.getResultList()
				.get(0);
	}

	@Override
	public boolean isCheckedIn(String memberCard, Integer lessonId) {
		long quantity = findCountByProperties(
				new String[]{"memberCard", "lesson.id"},
				new Object[]{memberCard, lessonId});
		return quantity>0;
	}

	@Override
	public boolean isCheckedIn(Integer distributerId, Integer lessonId) {
		long count = findCountByProperties(
				new String[]{"distributer.id", "lesson.id"}, 
				new Object[]{distributerId, lessonId});
		return count> 0;
	}

	@Override
	public List<LessonApiView> findLessonApiViewsByMemeberCard(String memberCard) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new com.yinzhiwu.yiwu.model.view.LessonApiView");
		hql.append("(");
		hql.append("t1.lesson.id");
		hql.append(",t1.lesson.name");
		hql.append(",t1.lesson.lessonDate");
		hql.append(",t1.lesson.actualTeacherName");
		hql.append(",t1.lesson.storeName");
		hql.append(")");
		hql.append(" FROM LessonCheckInYzw t1");
		hql.append(" WHERE t1.memberCard = :memberCard");
		hql.append(" AND t1.lesson.actualTeacher.id IS NOT NULL");
		hql.append(" AND t1.lesson.actualTeacher.id <>  0");
		hql.append(" ORDER BY t1.createTime DESC");
		
		return getSession().createQuery(hql.toString(),LessonApiView.class)
				.setParameter("memberCard", memberCard)
				.getResultList();
	}

	@Override
	public PageBean<LessonApiView> findPageCheckedInLessonApiViewsByMemberCard(String memberCard, Integer pageNo, Integer pageSize) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new " + LessonApiView.class.getName());
		hql.append("(");
		hql.append("t1.lesson.id");
		hql.append(",t1.lesson.name");
		hql.append(",t1.lesson.lessonDate");
		hql.append(",t1.lesson.actualTeacherName");
		hql.append(",t1.lesson.storeName");
		hql.append(")");
		hql.append(" FROM LessonCheckInYzw t1");
		hql.append(" WHERE t1.memberCard = :memberCard");
		hql.append(" AND t1.lesson.actualTeacher.id IS NOT NULL");
		hql.append(" AND t1.lesson.actualTeacher.id <>  0");
		hql.append(" ORDER BY t1.createTime DESC");
		
		return findPage(hql.toString(), LessonApiView.class, new String[]{"memberCard"}, new Object[]{memberCard}, pageNo, pageSize);
	}
	
	@Override
	public PageBean<LessonApiView> findPageOfLessonApiViewsByContractNo(String contractNo, int pageNo, int pageSize){
		Assert.hasLength(contractNo);
		
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new " + LessonApiView.class.getName());
		hql.append("(");
		hql.append("t1.lesson.id");
		hql.append(",t1.lesson.name");
		hql.append(",t1.lesson.lessonDate");
		hql.append(",t1.lesson.actualTeacherName");
		hql.append(",t1.lesson.storeName");
		hql.append(")");
		hql.append(" FROM LessonCheckInYzw t1");
		hql.append(" WHERE t1.contractNo = :contractNo");
		//老师也已签到
		hql.append(" AND t1.lesson.actualTeacher.id IS NOT NULL");
		hql.append(" AND t1.lesson.actualTeacher.id <>  0");
		hql.append(" ORDER BY t1.createTime DESC");
		
		return findPage(hql.toString(), LessonApiView.class, "contractNo", contractNo, pageNo, pageSize);
		
	}

	@Override
	public int findCheckedInLessonsCountByMemeberCard(String memberCard) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT COUNT(1)");
		hql.append(" FROM LessonCheckInYzw t1");
		hql.append(" WHERE t1.memberCard = :memberCard");
		hql.append(" AND t1.lesson.actualTeacher.id IS NOT NULL");
		hql.append(" AND t1.lesson.actualTeacher.id <>  0");
		return findCount(hql.toString(), "memberCard", memberCard).intValue();
	}

	@Override
	public StoreApiView findStoreApiViewOfLastCheckedOpenLesson(String memberCard) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new com.yinzhiwu.yiwu.model.view.StoreApiView");
		hql.append("(");
		hql.append("t1.lesson.store.id");
		hql.append(",t1.lesson.store.name");
		hql.append(",t1.lesson.store.parent.id");
		hql.append(")");
		hql.append(" FROM LessonCheckInYzw t1");
		hql.append(" WHERE t1.memberCard = :memberCard");
		hql.append(" AND t1.lesson.courseType= :courseType");
		hql.append(" ORDER BY t1.createTime DESC");
		
		List<StoreApiView> views = getSession().createQuery(hql.toString(), StoreApiView.class)
				.setParameter("memberCard", memberCard)
				.setParameter("courseType", CourseType.CLOSED)
				.setMaxResults(1)
				.getResultList();
		
		if(views.size()==0)
			return null;
		else
			return views.get(0);
		
	}


	@Override
	public LessonCheckInYzw findByLessonIdAndCoachId(Integer lessonId, Integer coachId) throws DataNotFoundException {
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM LessonCheckInYzw");
		hql.append(" WHERE lesson.id = :lessonId");
		hql.append(" AND teacher.id = :coachId");
		hql.append(" ORDER BY createTime");
		
		List<LessonCheckInYzw> lessons =  getSession().createQuery(hql.toString(),LessonCheckInYzw.class) 
					.setParameter("lessonId", lessonId)
					.setParameter("coachId", coachId)
					.setMaxResults(1)
					.getResultList();
		if(lessons.size()==0)
			throw  new DataNotFoundException("lesson id: " + lessonId + " 未找到教练: " + coachId + "的签到记录" );
		return lessons.get(0);
	}

}
