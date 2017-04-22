package com.yinzhiwu.springmvc3.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.entity.PlanRevenue;
import com.yinzhiwu.springmvc3.model.ReturnedJson;
import com.yinzhiwu.springmvc3.model.YiwuJson;
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
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("plan", new PlanRevenue());
		return "planRevenue/form";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public YiwuJson<PlanRevenue> doGet(@PathVariable int id){
		return new YiwuJson<>(planRevenueService.get(id));
		
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String doPost(@ModelAttribute PlanRevenue plan, Model model){
		planRevenueService.save(plan);
		return "success";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String doPut(PlanRevenue plan){
		planRevenueService.saveOrUpdate(plan);
		return "success";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String doDelete(@PathVariable int id){
		planRevenueService.delete(id);
		return "success";
	}
}
