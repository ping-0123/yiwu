package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.entity.yzwOld.Interval;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.IntervalService;

@Controller
@RequestMapping("api/interval")
public class IntervalController {
	
	@Autowired
	private IntervalService intervalService;
	
	@ResponseBody
	@RequestMapping(value="/getAllIntervals", method={RequestMethod.GET})
	public YiwuJson<List<Interval>> getAllIntervals(){
		return new YiwuJson<List<Interval>>(
				intervalService.getAllIntervals());
	}
}
