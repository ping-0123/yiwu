package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.model.ReturnedJson;
import com.yinzhiwu.springmvc3.service.RevenueService;

@Controller
@RequestMapping("/api/revenue")
public class RevenueController {
	
	@Autowired
	private RevenueService	revenueService;

	@ResponseBody
	@RequestMapping(value="/getMonthlyRevenue/{year}/{month}/{districtId}/{productTypeId}"
			, method={RequestMethod.GET})
	public ReturnedJson getMonthlyRevenue(@PathVariable int year,
										@PathVariable int month,
										@PathVariable int districtId,
										@PathVariable int productTypeId){
		
		return new ReturnedJson( 
				revenueService.getMonthlyRevenue(year,month, districtId, productTypeId));
	}
}
