package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.EmployeeApiView;
import com.yinzhiwu.yiwu.service.EmployeeService;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeApiController {
	
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
