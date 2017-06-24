package com.yinzhiwu.springmvc3.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yinzhiwu.springmvc3.dao.CheckInsYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.CheckInsYzw;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;

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
		String hql = "SELECT t1.lesson FROM CheckInsYzw t1 WHERE t1.contractNo in :contractNos";
		return (List<LessonYzw>) getHibernateTemplate().findByNamedParam(hql, "contractNos", contractNos);
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
