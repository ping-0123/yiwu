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
		@SuppressWarnings("unchecked")
		List<Long> list = (List<Long>) getHibernateTemplate().findByNamedParam(hql, "contractNos", contractNos);
		return list.get(0).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LessonYzw> findByContractNos(List<String> contractNos) {
		String hql = "from CheckInsYzw where contractNo in :contractNos";
		return (List<LessonYzw>) getHibernateTemplate().findByNamedParam(hql, "contractNos", contractNos);
	}

}
