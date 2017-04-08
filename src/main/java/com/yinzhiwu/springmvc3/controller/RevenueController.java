package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.model.ReturnedJson;
import com.yinzhiwu.springmvc3.service.RevenueService;

@Controller
@RequestMapping("/api/revenue")
public class RevenueController {
	
	@Autowired
	private RevenueService	revenueService;

	@ResponseBody
	@RequestMapping(value="/getMonthlyRevenue"
			, method={RequestMethod.GET})
	public ReturnedJson getMonthlyRevenue(@RequestParam int year,
										@RequestParam int month,
										@RequestParam int districtId,
										@RequestParam int productTypeId){
		
		return new ReturnedJson( 
				revenueService.getMonthlyRevenue(year,month, districtId, productTypeId));
	}
}
