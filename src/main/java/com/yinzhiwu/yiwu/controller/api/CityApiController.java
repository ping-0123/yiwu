package com.yinzhiwu.yiwu.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.service.CityService;

@Controller
@RequestMapping(value = "/api/city")
public class CityApiController {

	@Autowired
	private CityService cityService;
	
	@GetMapping(value="/getCities")
	@ResponseBody
	public List<String> getCities(){
		return cityService.getCities();
	}
	
}
