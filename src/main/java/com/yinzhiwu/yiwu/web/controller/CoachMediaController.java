package com.yinzhiwu.yiwu.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.CoachMedia;
import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.service.CoachMediaService;
import com.yinzhiwu.yiwu.service.EmployeePostYzwService;
import com.yinzhiwu.yiwu.service.EmployeeService;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;
import com.yinzhiwu.yiwu.service.FileService;
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
	@Qualifier("qiniuServiceImpl")
	@Autowired private FileService qiniuService;
	@Autowired private EmployeeYzwService employeeService;
	
	@GetMapping
	public String index(){
		return "redirect:coachMedia/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model){
		return "coachMedia/list";
	}
	
	@GetMapping(value="/{coachId}/updateForm")
	public String showUpdateForm(@PathVariable(name="coachId") Integer coachId, Model model){
		//添加七牛云上传token
		model.addAttribute("uploadToken", qiniuService.createAccessToken());
		
		// add header media
		CoachMedia headerMedia = coachMediaService.findHeaderMediaByCoachId(coachId);
		if(headerMedia==null) {
			headerMedia= new CoachMedia();
			EmployeeYzw coach = employeeService.get(coachId);
			headerMedia.setCoach(coach);
		}
		model.addAttribute("headerMedia", headerMedia);
		
		return "coachMedia/updateForm";
	}
	
	@PostMapping
	@ResponseBody
	public YiwuJson<?> createNew(@Valid CoachMedia coachMedia, BindingResult result){
		if(result.hasErrors())
			return YiwuJson.createByErrorMessage(getErrorsMessage(result));
		
		coachMediaService.saveOrUpdate(coachMedia);
		
		return YiwuJson.createBySuccess();
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
