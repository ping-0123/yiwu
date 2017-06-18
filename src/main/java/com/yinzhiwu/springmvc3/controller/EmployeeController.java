package com.yinzhiwu.springmvc3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.entity.yzw.EmployeeYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.EmployeeApiView;
import com.yinzhiwu.springmvc3.service.EmployeeService;
import com.yinzhiwu.springmvc3.service.EmployeeYzwService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeYzwService empYzwService;
	
	@RequestMapping(value="/getAllCoaches", method={RequestMethod.GET})
	public List<EmployeeApiView> getAllCoaches(){
		return employeeService.getAllOnJobCoaches();
	}
	
	@GetMapping(value="/list")
	public YiwuJson<List<EmployeeApiView>> list(EmployeeYzw e){
		try {
			List<EmployeeYzw> employees = empYzwService.findByExample(e);
			List<EmployeeApiView> views = new ArrayList<>();
			for (EmployeeYzw emp : employees) {
				views.add(new EmployeeApiView(emp));
			}
			return new YiwuJson<>(views);
		} catch (DataNotFoundException e1) {
			return new YiwuJson<>(e1.getMessage());
		}
	}
}
