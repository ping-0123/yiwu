package com.yinzhiwu.yiwu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CourseTemplateDao;
import com.yinzhiwu.yiwu.entity.CourseTemplate;
import com.yinzhiwu.yiwu.entity.LessonTemplate;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.CourseTemplateService;

/**
*@Author ping
*@Time  创建时间:2017年10月25日下午1:49:15
*
*/

@Service
public class CourseTemplateServiceImpl extends BaseServiceImpl<CourseTemplate,Integer> implements CourseTemplateService {
	
	@Autowired public void setBaseDao(CourseTemplateDao dao){super.setBaseDao(dao);}

	@Override
	public Integer save(CourseTemplate course) {
		List<LessonTemplate> lessons = new ArrayList<>();
		for(int i=0; i<course.getTimes(); i++){
			LessonTemplate lesson = new LessonTemplate();
			lesson.init();
			lesson.setCourseTemplate(course);
			lesson.setOrdinalNo(i+1);
			lessons.add(lesson);
		}
		course.setLessonTemplates(lessons);
		return super.save(course);
	}

	@Override
	public CourseTemplate findMapedCourseTemplate(CourseYzw course) {
		try {	
			return findOneByProperties(
					new String[]{"dance.id", "danceGrade.name","courseType"}, 
					new Object[]{course.getDance().getId(),
								course.getDanceGrade(),
								course.getCourseType()});
			
		} catch (DataNotFoundException e) {
			return null;
		}
	}
	
	
	
}
