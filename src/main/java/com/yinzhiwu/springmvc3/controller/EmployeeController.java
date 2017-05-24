package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.model.view.EmployeeApiView;
import com.yinzhiwu.springmvc3.service.EmployeeService;

@Controller
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@ResponseBody
	@RequestMapping(value="/getAllCoaches", method={RequestMethod.GET})
	public List<EmployeeApiView> getAllCoaches(){
		return employeeService.getAllOnJobCoaches();
	}
}
