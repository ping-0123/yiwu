package com.yinzhiwu.yiwu.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.YiwuJson.ReturnCode;
import com.yinzhiwu.yiwu.service.EmployeePostYzwService;

@Controller
@RequestMapping(value="/system/employeePosts")
public class EmployeePostController extends BaseController{

	@Autowired EmployeePostYzwService epService;
	
	@PostMapping
	@ResponseBody
	public YiwuJson<?> doCreate(@Valid EmployeePostYzw ep, BindingResult result){
		if(result.hasErrors())
			return YiwuJson.createByErrorCodeMessage(ReturnCode.ILLEGAL_ARGUMENT.getCode(), getErrorsMessage(result));
		
		
		epService.save(ep);
		return YiwuJson.createBySuccess();
	}
	
	@GetMapping(value="/{id}/updateForm")
	public String showUpdateForm(@PathVariable(name="id") Integer id, Model model) throws DataNotFoundException{
		model.addAttribute("ep", epService.get(id));
		return "employeePosts/updateForm";
	}
	
	@PutMapping(value="/{id}")
	@ResponseBody
	public YiwuJson<?> doPut(@PathVariable(name="id") Integer id, EmployeePostYzw ep){
		try {
			epService.modify(id, ep);
			return YiwuJson.createBySuccess();
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
	
	@ResponseBody
	@DeleteMapping(value="/{id}")
	public YiwuJson<?> delete(@PathVariable(name="id") Integer id){
		epService.delete(id);
		return YiwuJson.createBySuccess();
	}
}
