package com.yinzhiwu.yiwu.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.view.DepartmentZtreeApiView;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;

/**
 * 
 * @author ping
 * @date 2017年9月19日下午8:24:05
 *
 */
@Controller
@RequestMapping(value="/system/departments")
public class DepartmentController extends BaseController {

	@Autowired private DepartmentYzwService deptService;
	
	@GetMapping
	public String index(){
		return "redirect:departments/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model){
		return "departments/list";
	}
	
	@GetMapping(value="/createForm")
	public String showAddChildrenForm(Integer parentId, Model model){
		model.addAttribute("parentId", parentId);
		return "departments/createForm";
	}
	
	
	@GetMapping("/{id}/updateForm")
	public String showModifyForm(@PathVariable(name="id") Integer id, Model model){
		DepartmentYzw dept = deptService.get(id);
		model.addAttribute("dept", dept);
		return "departments/updateForm";
	}
	
	@GetMapping("/{id}/detailJsp")
	public String showDetail(@PathVariable(name="id") Integer id, Model model){
		DepartmentYzw dept = deptService.get(id);
		model.addAttribute("dept", dept);
		return "departments/detail";
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public YiwuJson<?> get(@PathVariable(name="id") Integer id){
		return YiwuJson.createBySuccess(deptService.get(id));
	}
	
	@GetMapping(value="/ztree")
	@ResponseBody
	public YiwuJson<?> getZtree(){
		try {
			List<DepartmentYzw> depts = deptService.findAll();
			List<DepartmentZtreeApiView> views = new ArrayList<DepartmentZtreeApiView>();
			for (DepartmentYzw dept : depts) {
				views.add(new DepartmentZtreeApiView(
						dept.getId(), 
						dept.getParent()==null?null:dept.getParent().getId(),
						dept.getName(),
						//TODO 人性化的判断该节点是否因该展开
						true));
			}
			
			return YiwuJson.createBySuccess(views);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
	
	
	@PostMapping
	@ResponseBody
	public YiwuJson<?> createNewPost(@Valid DepartmentYzw dept, BindingResult bindingResult){
		if(bindingResult.hasErrors() ){	
			return YiwuJson.createByError();
		}
		deptService.save(dept);
		return YiwuJson.createBySuccess(dept);
	}
	
	@PutMapping(value ="/{id}")
	@ResponseBody
	public YiwuJson<?> update(@PathVariable(name="id") Integer id, DepartmentYzw dept){
		try {
			deptService.modify(id, dept);
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			logger.error(e.getMessage(), e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
		
		return YiwuJson.createBySuccess();
	}
	
	
	@ResponseBody
	@DeleteMapping(value="/{id}")
	public YiwuJson<?> delete(@PathVariable(name="id") Integer id){
		try {
			deptService.delete(id);
			return YiwuJson.createBySuccess();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
	
	
	@ResponseBody
	@PostMapping(value="/datatable")
	public DataTableBean<?> getDatatable(HttpServletRequest request){
		try {
			QueryParameter para = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
			return deptService.findDataTable(para);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		return null;
	}
	
}
