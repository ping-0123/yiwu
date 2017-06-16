package com.yinzhiwu.springmvc3.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.entity.yzwOld.Lesson;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;
import com.yinzhiwu.springmvc3.service.LessonService;
import com.yinzhiwu.springmvc3.service.LessonYzwService;

/**
 * @since 用formater and validate
 * @author ping
 * @date 2017-03-20
 */


@CrossOrigin
@Controller
@RequestMapping(value="api/lesson")
public class LessonController {

	@Autowired
	@Qualifier(value="lessonServiceImplTwo")
	private LessonService lessonService;
	
	@Autowired
	private LessonYzwService lessonYzwService;
	
	
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
			@RequestParam String weChat){
		
		if (date == null){
			date = new Date();
		}
		return lessonService.findLessonWeekList(
				storeId, courseType, teacherName, danceCatagory, date, weChat);
	}
	
	@RequestMapping(value="arrangePriviteLesson", method={RequestMethod.POST})
	public void arrangePriviteLesson(@ModelAttribute Lesson lesson){
		lessonService.save(lesson);
	}
	
	@GetMapping(value="/list")
	public YiwuJson<List<LessonApiView>> findByCourseId(String courseId){
		return lessonYzwService.findByCourseId(courseId);
	}
}
