package com.yinzhiwu.yiwu.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.SequenceDao;
import com.yinzhiwu.yiwu.entity.sys.Sequence;

@Repository
public class SequenceDaoImpl implements SequenceDao {

	@Autowired private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Integer getValue(String sequenceName) {
		Sequence sequence  = getSession().get(Sequence.class, sequenceName);
		int value = sequence.getValue();
		sequence.increase();
		getSession().save(sequence);
		return value;
	}
	
	
}
