package com.yinzhiwu.springmvc3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	
	public String testArray(String[] name){
		StringBuilder builder = new StringBuilder();
		for (String string : name) {
			builder.append(string);
		}
		return builder.toString();
	}

}
