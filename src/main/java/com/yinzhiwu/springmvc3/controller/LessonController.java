package com.yinzhiwu.springmvc3.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.type.TrueFalseType;
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

import com.yinzhiwu.springmvc3.entity.yzw.Connotation;
import com.yinzhiwu.springmvc3.entity.yzwOld.Lesson;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;
import com.yinzhiwu.springmvc3.service.LessonService;
import com.yinzhiwu.springmvc3.service.LessonYzwService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @since 用formater and validate
 * @author ping
 * @date 2017-03-20
 */


@CrossOrigin
@Controller
@RequestMapping(value="api/lesson")
public class LessonController  extends BaseController{

	@Autowired
	@Qualifier(value="lessonServiceImplTwo")
	private LessonService lessonService;
	
	@Autowired
	private LessonYzwService lessonYzwService;
	
	@Deprecated
	@RequestMapping(value="/id/{id}" ,method={RequestMethod.GET})
	@ResponseBody
	public Lesson getLesson(@PathVariable String id){
		int intId = Integer.parseInt(id);
		return lessonService.findById(intId);
	}
	
	@RequestMapping(value="/{id}" ,method={RequestMethod.GET})
	@ResponseBody
	public Lesson getLesson2(@PathVariable String id){
		int intId = Integer.parseInt(id);
		return lessonService.findById(intId);
	}
	
	@GetMapping(value="/connotation/{lessonId}")
	@ResponseBody
	@ApiOperation(value="根据课时Id获取课时内涵信息")
	public YiwuJson<Connotation> getConnotationByLessonId(@PathVariable int lessonId){
		try {
			return new YiwuJson<>(lessonYzwService.get(lessonId).getConnotation());
		} catch (DataNotFoundException e) {
			logger.error(e);
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@GetMapping(value="/connotation/getLastNLessonsConnotation")
	@ResponseBody
	@ApiOperation(value="获取本节课前N节课的课时内涵")
	public YiwuJson<Connotation> getLastNLessonsConnotation(
			@ApiParam(value="本节课的课时Id", required=true)Integer thisLessonId, 
			@ApiParam(value="第前lastN节课, 为0表示返回本节课, 为负返回第后-lastN节课", required=true )Integer lastN)
	{
		try{
			Connotation connotation = lessonYzwService.getLastNLessonConnotation(thisLessonId, lastN);
			if(connotation != null)
				return new YiwuJson<>(connotation);
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
		return new YiwuJson<>("不存在前" + lastN + "课时");
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
