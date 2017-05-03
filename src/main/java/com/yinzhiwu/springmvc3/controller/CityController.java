package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.service.CityService;

@Controller
@RequestMapping(value = "/api/city")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@RequestMapping(value="/getCities")
	@ResponseBody
	public List<String> getCities(){
		return cityService.getCities();
	}
	
}
