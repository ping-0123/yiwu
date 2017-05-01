package com.yinzhiwu.springmvc3.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.entity.Test;
import com.yinzhiwu.springmvc3.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	public String testArray(String[] name){
		StringBuilder builder = new StringBuilder();
		for (String string : name) {
			builder.append(string);
		}
		return builder.toString();
	}
	
	@GetMapping("/show")
	@ResponseBody
	public Object show(){
		return testService.get(new Long(1));
	}
	
	@GetMapping("/save")
	@ResponseBody
	public Object save(){
		Test t= new Test();
		t.setName("test2");
		t.setCreateDate(new Date());
		t.setCreateUserId(1);
		t.setLastModifiedUserId(1);
		return testService.save(t);
	}

}
