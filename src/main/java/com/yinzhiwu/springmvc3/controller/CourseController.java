package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CourseApiView;

@RestController
@RequestMapping(value="/api/course")
public class CourseController {

	@Autowired
	private CourseYzwService courseYzwService;
	
	@GetMapping("/id{id}")
	public YiwuJson<CourseApiView> findById(@PathVariable String id){
		return courseYzwService.findById(id);
	}
	
}
