package com.yinzhiwu.yiwu.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;


@Controller
@RequestMapping(value="/system/employees")
public class EmployeeController  extends BaseController{

	@Autowired private EmployeeYzwService employeeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model){
		List<EmployeeYzw> employees = employeeService.findAll();
		model.addAttribute("employees", employees);
		return "employees/list";
	}
}
