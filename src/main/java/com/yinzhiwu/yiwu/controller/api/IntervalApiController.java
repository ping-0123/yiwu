package com.yinzhiwu.yiwu.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.entity.yzwOld.Interval;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.IntervalService;

@Controller
@RequestMapping("api/interval")
public class IntervalApiController {

	@Autowired
	private IntervalService intervalService;

	@ResponseBody
	@RequestMapping(value = "/getAllIntervals", method = { RequestMethod.GET })
	public YiwuJson<List<Interval>> getAllIntervals() {
		return new YiwuJson<List<Interval>>(intervalService.getAllIntervals());
	}
}
