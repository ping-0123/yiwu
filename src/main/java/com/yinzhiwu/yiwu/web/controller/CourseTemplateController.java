package com.yinzhiwu.yiwu.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.service.CourseTemplateService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;

/**
*@Author ping
*@Time  创建时间:2017年10月25日下午1:56:41
*
*/

@Controller
@RequestMapping("/system/courseTemplates")
public class CourseTemplateController extends BaseController{
	
	@Autowired CourseTemplateService courseTemplateService;
	
	@GetMapping(value="/list")
	public String list(){
		return "courseTemplates/list";
	}
	
	@GetMapping
	public String home(){
		return "redirect:courseTemplates/list";
	}
	
	@GetMapping(value="/index")
	public String index(){
		return "redirect:list";
	}
	
	@GetMapping(value="/createForm")
	public String showCreateForm(){
		return "courseTemplates/createForm";
	}
	
	@PostMapping(value="/datatable")
	@ResponseBody
	public DataTableBean<?> findDatatable(HttpServletRequest request){
		
		try {
			QueryParameter parameter = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
			return courseTemplateService.findDataTable(parameter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
				| InstantiationException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		return new DataTableBean<>();
	}
}
