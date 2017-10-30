package com.yinzhiwu.yiwu.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.ConnotationProvider;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.service.ConnotationProviderService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;


/**
* @author 作者 ping
* @Date 创建时间：2017年10月30日 下午11:21:12
*
*/

@Controller
@RequestMapping("/system/connotationProviders")
public class ConnotationProviderController extends BaseController{
	
	@Autowired private ConnotationProviderService connotationProviderService;
	
	@RequestMapping("/list")
	public String list(){
		return "connotationProviders/list";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "redirect:list";
	}
	
	@RequestMapping
	public String home(){
		return "redirect:connotationProviders/list";
	}
	
	@RequestMapping("/createForm")
	public String showCreateForm(){
		return "connotationProviders/createForm";
	}
	
	@RequestMapping("/{id}/updateForm")
	public String showUpdateForm(@PathVariable(name="id") Integer id, Model model) throws DataNotFoundException
	{
		model.addAttribute("provider", connotationProviderService.get(id));
		return "connotationProviders/updateForm";
	}
	
	@PostMapping("/datatable")
	@ResponseBody
	public DataTableBean<?> getDatatable(HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException
	{
		
		QueryParameter parameter = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
		return connotationProviderService.findDataTable(parameter);
	}
	
	@PostMapping
	@ResponseBody
	public YiwuJson<?> doCreate(@Valid ConnotationProvider provider, BindingResult result)
	{
		if(result.hasErrors())
			return YiwuJson.createByErrorMessage(getErrorsMessage(result));
		connotationProviderService.save(provider);
		
		return YiwuJson.createBySuccess();
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public YiwuJson<?> doUpdate(@PathVariable(name="id") Integer id ,
			@Valid ConnotationProvider provider, BindingResult result) throws IllegalArgumentException, IllegalAccessException, DataNotFoundException{
		if(result.hasErrors())
			return YiwuJson.createByErrorMessage(getErrorsMessage(result));
		
		connotationProviderService.modify(id, provider);
		
		return YiwuJson.createBySuccess();
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public YiwuJson<?> doDelete(@PathVariable(name="id") Integer id)
	{
		connotationProviderService.delete(id);
		return YiwuJson.createBySuccess();
	}
}
