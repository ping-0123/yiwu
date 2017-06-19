package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.model.ReturnedJson;
import com.yinzhiwu.springmvc3.service.AppointmentService;

/**
 * 
 * @author ping
 */

@Controller
@RequestMapping("/api/appointment")
public class AppointmentController extends BaseController{

	@Autowired
	private AppointmentService appointmentService;
	
	@ResponseBody
	@RequestMapping(value="/appoint", method={RequestMethod.POST})
	public ReturnedJson appoint(@RequestParam int customerId,
								@RequestParam int lessonId){
		return new ReturnedJson(
				appointmentService.appoint(customerId, lessonId));
	}
	
	@ResponseBody
	@RequestMapping(value="/unappoint",method={RequestMethod.PUT})
	public ReturnedJson unAppoint(@RequestParam int customerId,
								@RequestParam int lessonId){
		return new ReturnedJson(
				appointmentService.unAppoint(customerId, lessonId));
	}
	
	@ResponseBody
	@RequestMapping(value="/getStatus", method={RequestMethod.GET})
	public ReturnedJson getStatus(@RequestParam int customerId,
								@RequestParam int lessonId){
		return new ReturnedJson(
				appointmentService.getStatus(customerId, lessonId));
	}
}
