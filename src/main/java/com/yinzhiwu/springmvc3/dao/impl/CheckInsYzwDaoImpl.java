package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.CheckInsYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.CheckInsYzw;
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
		String hql = "from CheckInsYzw where contractNo in :contractNos";
		return (List<LessonYzw>) getHibernateTemplate().findByNamedParam(hql, "contractNos", contractNos);
	}

}
