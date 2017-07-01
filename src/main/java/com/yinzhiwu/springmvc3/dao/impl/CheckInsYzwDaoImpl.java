package com.yinzhiwu.springmvc3.dao.impl;

import java.util.ArrayList;
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

import com.yinzhiwu.springmvc3.dao.CheckInsYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.CheckInsYzw;
import com.yinzhiwu.springmvc3.entity.yzw.CourseYzw;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.model.page.PageBean;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;

@Repository
public class CheckInsYzwDaoImpl extends BaseDaoImpl<CheckInsYzw, Integer> implements CheckInsYzwDao {

	@Override
	public int findCountByContractNos(List<String> contractNos) {
		String hql = "select count(*) from CheckInsYzw where contractNo in :contractNos";
		if(contractNos==null || contractNos.size() ==0)
			return 0;
		@SuppressWarnings("unchecked")
		List<Long> list = (List<Long>) getHibernateTemplate().findByNamedParam(hql, "contractNos", contractNos);
		return list.get(0).intValue();
	}

	@Override
	public int findCountByCustomerId(int customerId){
		StringBuilder builder = new StringBuilder("select count(*) from CheckInsYzw t1 join OrderYzw t2 on(t1.contractNo = t2.contract.contractNo)");
		builder.append("where t2.customer.id =:customerId");
		@SuppressWarnings("unchecked")
		List<Long> counts = (List<Long>) getHibernateTemplate().findByNamedParam(builder.toString(), "customerId", customerId);
		return counts.get(0).intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LessonYzw> findByContractNos(List<String> contractNos) {
		if(contractNos==null || contractNos.size()==0)
			return new ArrayList<>();
		String hql = "SELECT t1.lesson FROM CheckInsYzw t1 WHERE t1.contractNo in :contractNos";
		return (List<LessonYzw>) getHibernateTemplate().findByNamedParam(hql, "contractNos", contractNos);
	}
	
	@Override
	public PageBean<LessonApiView> findPageByContractNos(List<String> contractNos, int pageNo,int pageSize ) {
		if(contractNos==null || contractNos.size()==0) return null;
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<LessonApiView> criteria = builder.createQuery(LessonApiView.class);
		Root<?> checkIn = criteria.from(CheckInsYzw.class);
		Join<CheckInsYzw,LessonYzw> lessonJoin = checkIn.join("lesson", JoinType.LEFT);
		Join<LessonYzw,CourseYzw> courseJoin = lessonJoin.join("course", JoinType.LEFT);
//		Path<LessonYzw> lesson = checkIn.get("lesson");
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
				lessonJoin.get("dueTeacherName")
				));
		javax.persistence.criteria.Predicate condition = checkIn.get("contractNo").in(contractNos);
		criteria.where(condition);
		criteria.orderBy(builder.desc(lessonJoin.get("lessonDate")), builder.desc(lessonJoin.get("startTime")));
		
		//找出记录总数量
		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<CheckInsYzw> countRoot = countCriteria.from(CheckInsYzw.class);
		countCriteria.select( builder.count(countRoot));
		Predicate countPredicate = countRoot.get("contractNo").in(contractNos);
		countCriteria.where(countPredicate);
		Long totalCount = getSession().createQuery(countCriteria).getSingleResult();
		
		return findPageByCriteria(criteria, pageNo, pageSize, totalCount.intValue());
//		String hql = "FROM CheckInsYzw t1 WHERE t1.contractNo in :contractNos order by t1.createTime desc";
//		PageBean<CheckInsYzw> page = findPageByHql(
//				hql, pageNo, pageSize, new String[]{"contractNos"}, new Object[]{contractNos});
//		List<LessonYzw> lessons  = new ArrayList<>();
//		if(page.getData() ==null || page.getData().size() ==0) return null; 
//		for (CheckInsYzw checkIns : page.getData()) {
//			lessons.add(checkIns.getLesson());
//		}
//		return new PageBean<>(page.getPageSize(), page.getCurrentPage(), page.getTotalRecord(), lessons);
	}

	@Override
	public boolean isCheckedIn(CustomerYzw customer, LessonYzw lesson) {
		Assert.notNull(customer);
		Assert.notNull(lesson);
		
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from CheckInsYzw t1 where t1.lesson.id=:lessonId");
		hql.append(" and t1.contractNo in");
		hql.append("(select t1.contract.contractNo from OrderYzw t1 where t1.contract.status='已审核' and t1.contract.subType=:subCourseType");
		hql.append(" and t1.contract.remainTimes>=1 and t1.contract.end >= :currdate)");
		@SuppressWarnings("unchecked")
		List<Long> counts = (List<Long>) getHibernateTemplate().findByNamedParam(
				hql.toString(), 
				new String[]{"lessonId", "subCourseType", "currdate"}, 
				new Object[]{lesson.getId(), lesson.getSubCourseType(), new Date()});
		if(counts.get(0) > 0)
			return true;
		return false;
	}

	

}
