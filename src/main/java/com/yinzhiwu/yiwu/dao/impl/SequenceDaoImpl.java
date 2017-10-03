package com.yinzhiwu.yiwu.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.SequenceDao;
import com.yinzhiwu.yiwu.entity.sys.Sequence;

@Repository
public class SequenceDaoImpl implements SequenceDao {

	@Autowired private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private Integer getValue(String sequenceName) {
		Assert.hasText(sequenceName);
		
		Sequence sequence  = getSession().get(Sequence.class, sequenceName);
		if(sequence==null)
			sequence = new Sequence(sequenceName,1);
		int value = sequence.getValue();
		sequence.increase();
		getSession().save(sequence);
		return value;
	}

	@Override
	public Integer getEmployeeNumber() {
		return getValue(Sequence.EMPLOYEE_NUMBER);
	}
	
	
}
