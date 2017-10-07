package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CourseYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.service.CourseYzwService;

@Service
public class CourseYzwServiceImpl extends BaseServiceImpl<CourseYzw, String> implements CourseYzwService {

	@SuppressWarnings("unused")
	@Autowired private CourseYzwDao courseDao;

	@Autowired
	public void setBaseDao(CourseYzwDao courseYzwDao) {
		super.setBaseDao(courseYzwDao);
	}

}
