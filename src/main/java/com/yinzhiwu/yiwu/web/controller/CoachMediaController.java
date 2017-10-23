package com.yinzhiwu.yiwu.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.service.CoachMediaService;
import com.yinzhiwu.yiwu.service.EmployeePostYzwService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;

/**
*@Author ping
*@Time  创建时间:2017年10月23日上午11:08:59
*
*/

@Controller
@RequestMapping(value="/system/coachMedia")
public class CoachMediaController extends BaseController{
	
	@Autowired private CoachMediaService coachMediaService;
	@Autowired private EmployeePostYzwService employeePostService;
	
	@GetMapping
	public String index(){
		return "redirect:coachMedia/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model){
		return "coachMedia/list";
	}
	
	
	
	@PostMapping(value="/datatable")
	@ResponseBody
	public DataTableBean<EmployeePostYzw> findDatatable(HttpServletRequest request){
		
		try {
			QueryParameter parameter = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
			return employeePostService.findDataTableOfCoach(parameter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
				| InstantiationException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		return null;
	}

}
