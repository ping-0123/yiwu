package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.model.ReturnedJson;
import com.yinzhiwu.springmvc3.service.PlanRevenueService;

@Controller
@RequestMapping(value="/api/planRevenue")
public class PlanRevenueController {
	
	@Autowired
	private PlanRevenueService planRevenueService;
	
	@ResponseBody
	@RequestMapping(value="/getMonthlyPlanRevenue"
			, method={RequestMethod.GET})
	public ReturnedJson getMonthlyPlanRevenue(@RequestParam int districtId,
											@RequestParam int year,
											@RequestParam int month,
											@RequestParam int productTypeId){
		
		return new ReturnedJson(
				planRevenueService.getDistricMonthlyPlanRevenue(districtId, year, month, productTypeId));
	}
}
