package com.yinzhiwu.yiwu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.SequenceDao;

@Service
public class SequenceServiceImpl implements SequenceService {
	
	@Autowired private SequenceDao sequenceDao;

	@Override
	public Integer getValue(String sequenceName) {
		return sequenceDao.getValue(sequenceName);
	}
	
	
}
