package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.IntervalDao;
import com.yinzhiwu.yiwu.entity.yzwOld.Interval;
import com.yinzhiwu.yiwu.service.IntervalService;

@Service
public class IntervalServiceImpl implements IntervalService {

	@Autowired
	private IntervalDao intervalDao;

	@Override
	public List<Interval> getAllIntervals() {
		return intervalDao.findAll();
	}

}
