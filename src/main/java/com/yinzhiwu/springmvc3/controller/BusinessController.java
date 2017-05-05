package com.yinzhiwu.springmvc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.model.SumRecordApiView;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.BusinessService;

@RestController
@RequestMapping("/api/business")
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	@GetMapping(value="/getperformance")
	public YiwuJson<SumRecordApiView>  getPerformance(int distributerId){
		return businessService.getPerformance(distributerId);
	}

}
