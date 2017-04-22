package com.yinzhiwu.springmvc3.controller;


import javax.persistence.AttributeOverride;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.entity.PlanRevenue;
import com.yinzhiwu.springmvc3.model.ReturnedJson;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.DepartmentService;
import com.yinzhiwu.springmvc3.service.PlanRevenueService;


@Controller
@RequestMapping(value="/api/planRevenue")
public class PlanRevenueController {
	
	 private static final Log logger = LogFactory.getLog(PlanRevenueController.class);
	
	@Autowired
	private PlanRevenueService planRevenueService;
	
	@Autowired
	private DepartmentService departmentService;
	
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
		model.addAttribute("stores",departmentService.getAllStores());
		return "planRevenue/form";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public YiwuJson<PlanRevenue> doGet(@PathVariable int id){
		return new YiwuJson<>(planRevenueService.get(id));
		
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String doPost(@Valid @ModelAttribute("plan")PlanRevenue plan, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors())
		{
			 FieldError fieldError = bindingResult.getFieldError();
			 logger.info("Code:" + fieldError.getCode() + ",field:" + fieldError.getField());
	         return "planRevenue/form";
		}
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
