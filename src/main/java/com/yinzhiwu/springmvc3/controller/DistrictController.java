package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.entity.Department;
import com.yinzhiwu.springmvc3.model.BriefDepartment;
import com.yinzhiwu.springmvc3.service.DepartmentService;

@CrossOrigin
@Controller
@RequestMapping(value="/api/district")
public class DistrictController {
	@SuppressWarnings("unused")
	private static final Log logger =
			LogFactory.getLog(DistrictController.class);
	
	@Autowired
	private DepartmentService s;
	
	@RequestMapping(value="/list", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<BriefDepartment> getDistrictList(){
		return s.findAllOperationDistricts();
	}
	
	
	@RequestMapping(value="/id/{id}",  method={RequestMethod.GET})
	@ResponseBody
	public Department findById(@PathVariable String id){
		int intId = Integer.parseInt(id);
		return s.findById(intId);
	}
	
}
