package com.yinzhiwu.springmvc3.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.entity.Lesson;
import com.yinzhiwu.springmvc3.service.LessonService;

/**
 * @since 用formater and validate
 * @author ping
 * @date 2017-03-20
 */


@CrossOrigin
@Controller
@RequestMapping(value="api/lesson")
public class LessonControl {

	@Autowired
	@Qualifier(value="lessonServiceImplTwo")
	private LessonService lessonService;
	
	
	@RequestMapping(value="/id/{id}" ,method={RequestMethod.GET})
	@ResponseBody
	public Lesson getLesson(@PathVariable String id){
		int intId = Integer.parseInt(id);
		return lessonService.findById(intId);
	}
	
	@RequestMapping(value="weeklist", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object getLessonWeekList(
			@RequestParam int storeId,
			@RequestParam String courseType,
			@RequestParam String teacherName,
			@RequestParam String danceCatagory,
			@RequestParam Date date,
			@RequestParam Customer weChat){
		
		if (date == null){
			date = new Date();
		}
		return lessonService.findLessonWeekList(
				storeId, courseType, teacherName, danceCatagory, date, weChat);
	}
	
	
}
