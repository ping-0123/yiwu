package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.IntervalDao;
import com.yinzhiwu.springmvc3.entity.yzwOld.Interval;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.service.IntervalService;

@Service
public class IntervalServiceImpl implements IntervalService{

	
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
