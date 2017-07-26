package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CourseYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.CourseApiView;
import com.yinzhiwu.yiwu.service.CourseYzwService;

@Service
public class CourseYzwServiceImpl extends BaseServiceImpl<CourseYzw, String> implements CourseYzwService {

	@Autowired
	private CourseYzwDao courseDao;

	@Autowired
	public void setBaseDao(CourseYzwDao courseYzwDao) {
		super.setBaseDao(courseYzwDao);
	}

	@Override
	public YiwuJson<CourseApiView> findById(String id) {
		try {
			CourseYzw course = courseDao.get(id);
			return new YiwuJson<>(new CourseApiView(course));
		} catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

}
