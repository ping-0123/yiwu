package com.yinzhiwu.yiwu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.IntervalDao;
import com.yinzhiwu.yiwu.entity.yzwOld.Interval;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.IntervalService;

@Service
public class IntervalServiceImpl implements IntervalService {

	@Autowired
	private IntervalDao intervalDao;

	@Override
	public List<Interval> getAllIntervals() {
		try {
			return intervalDao.findAll();
		} catch (DataNotFoundException e) {
			return new ArrayList<>();
		}
	}

}
