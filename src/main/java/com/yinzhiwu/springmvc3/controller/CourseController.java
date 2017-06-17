package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.entity.yzw.Connotation;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CourseApiView;
import com.yinzhiwu.springmvc3.service.CourseYzwService;

@RestController
@RequestMapping(value="/api/course")
public class CourseController extends BaseController{

	@Autowired
	private CourseYzwService courseYzwService;
	
	@Deprecated
	@GetMapping("/id{id}")
	public YiwuJson<CourseApiView> findById(@PathVariable String id){
		return courseYzwService.findById(id);
	}
	
	@GetMapping("/{id}")
	public YiwuJson<CourseApiView> doGet(@PathVariable String id){
		return courseYzwService.findById(id);
	}
	
	@GetMapping("/connotation/{courseId}")
	public YiwuJson<Connotation> getConnotationByCourseId(@PathVariable String courseId){
		try {
			return new YiwuJson<>(courseYzwService.get(courseId).getConnotation());
		} catch (DataNotFoundException e) {
			logger.debug(e);
			return new YiwuJson<>(e.getMessage());
		}
	}
}
