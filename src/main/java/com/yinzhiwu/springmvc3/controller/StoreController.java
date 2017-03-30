package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.model.BriefDepartment;
import com.yinzhiwu.springmvc3.service.DepartmentService;

//@CrossOrigin
@Controller
@RequestMapping(value="/api/store")
public class StoreController {
	
	@Autowired
	private DepartmentService s;
	
	@RequestMapping(value="/list", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<BriefDepartment> getStoreList(@RequestParam String districtId){
		return s.findStoresByDistrictId(Integer.parseInt(districtId));
	}
	

}
