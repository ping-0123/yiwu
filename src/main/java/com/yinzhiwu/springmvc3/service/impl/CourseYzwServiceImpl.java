package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.CourseYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.CourseYzw;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CourseApiView;
import com.yinzhiwu.springmvc3.service.CourseYzwService;


@Service
public class CourseYzwServiceImpl  extends BaseServiceImpl<CourseYzw,String> implements CourseYzwService{

	@Autowired
	private CourseYzwDao courseDao;
	
	@Autowired
	public void setBaseDao(CourseYzwDao courseYzwDao){
		super.setBaseDao(courseYzwDao);
	}
	
	@Override
	public YiwuJson<CourseApiView> findById(String id) {
		CourseYzw course = courseDao.get(id);
		if(course == null)
			return new YiwuJson<>("not found by id:" + id);
		return new YiwuJson<>(new CourseApiView(course));
	}

}
