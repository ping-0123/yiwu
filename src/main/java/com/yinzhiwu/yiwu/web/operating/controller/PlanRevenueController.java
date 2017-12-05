package com.yinzhiwu.yiwu.web.operating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.service.PlanRevenueService;

@Controller
@RequestMapping("/system/plans")
public class PlanRevenueController extends BaseController{
	
	@Autowired private PlanRevenueService planService;
	
	
}
