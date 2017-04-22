package com.yinzhiwu.springmvc3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	
	
	@ResponseBody
	@RequestMapping(value="arr", method=RequestMethod.GET)
	public String testArray(String[] name){
		StringBuilder builder = new StringBuilder();
		for (String string : name) {
			builder.append(string);
		}
		return builder.toString();
	}

}
