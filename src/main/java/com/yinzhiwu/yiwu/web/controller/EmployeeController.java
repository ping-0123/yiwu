package com.yinzhiwu.yiwu.web.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.YiwuJson.ReturnCode;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.view.EmployeeVO;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.service.EmployeePostYzwService;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.service.PostYzwService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;


@Controller
@RequestMapping(value="/system/employees")
public class EmployeeController  extends BaseController{

	@Autowired private EmployeeYzwService employeeService;
	@Autowired private DepartmentYzwService deptService;
	@Autowired private PostYzwService postService;
	@Autowired private EmployeePostYzwService epService;
	@Qualifier("qiniuServiceImpl")
	@Autowired private FileService qiniuService;
	
	@GetMapping
	public String index(){
		return "redirect:employees/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model){
		return "employees/list";
	}
	
	@GetMapping(value="/form")
	public String showCreateForm(){
		return "employees/createForm";
	}
	
	@GetMapping(value="/{id}/updateForm")
	public String showModifyForm(@PathVariable(name="id") Integer id, Model model) throws DataNotFoundException{
		EmployeeYzw employee = employeeService.get(id);
		EmployeeVO vo = EmployeeVO.converter.fromPO(employee);
		model.addAttribute("employee",vo);
		model.addAttribute("uploadToken", qiniuService.createAccessToken());
		return "employees/updateForm";
	}
	
	@PostMapping(value="/datatable")
	@ResponseBody
	public DataTableBean<?> findDatatable(HttpServletRequest request){
		
		try {
			QueryParameter parameter = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
			return employeeService.findDataTable(parameter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
				| InstantiationException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GetMapping(value="/{id}")
	@ResponseBody
	public YiwuJson<?> get(@PathVariable(name="id") Integer id ) throws DataNotFoundException{
		return YiwuJson.createBySuccess(employeeService.get(id));
	}
	
	@GetMapping(value="/{id}/posts/list")
	public String getPostsListJsp(@PathVariable(name="id") Integer id , Model model) throws DataNotFoundException{
		EmployeeYzw e = employeeService.get(id);
		model.addAttribute("employee", e);
		return "employees/posts/list";
	}
	
	@GetMapping(value="/{id}/posts/createForm")
	public String getPostsCreateForm(@PathVariable(name="id") Integer id, Model model) throws DataNotFoundException{
		model.addAttribute("employee", employeeService.get(id));
		model.addAttribute("departments", deptService.findAll());
		model.addAttribute("posts", postService.findAll());
		Calendar calendar = Calendar.getInstance();
		model.addAttribute("startTime", calendar.getTime());
		calendar.add(Calendar.YEAR, 1);
		model.addAttribute("endTime", calendar.getTime());
		return "employees/posts/createForm";
	}
	
	@PostMapping(value="/{id}/posts")
	@ResponseBody
	public YiwuJson<?> createPosts(@Valid EmployeePostYzw ep, BindingResult result){
		if(result.hasErrors())
			return YiwuJson.createByErrorCodeMessage(ReturnCode.ILLEGAL_ARGUMENT.getCode(), getErrorsMessage(result));
		
		try {
			epService.save(ep);
			return YiwuJson.createBySuccess();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
	
	@PostMapping(value="/{id}/posts/datatable")
	@ResponseBody
	public DataTableBean<?> findPostsDatatable(@PathVariable(name="id") Integer id, HttpServletRequest request){
		try {
			QueryParameter parameter = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
			return epService.findDataTableByEmployeeId(parameter,id);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			logger.error(e.getMessage(),e);
		}
		
		return null;
	}
	
	@PostMapping
	@ResponseBody
	public YiwuJson<?> create(@Valid EmployeeYzw employee, BindingResult bindingResult){
		if(bindingResult.hasErrors() ){	
			return YiwuJson.createByErrorMessage(getErrorsMessage(bindingResult));
		}
		employeeService.save(employee);
		return YiwuJson.createBySuccess(employee);
	}
	
	@PutMapping(value="/{id}")
	@ResponseBody
	public YiwuJson<?> modify(@PathVariable(name="id") Integer id, EmployeeYzw employee){
		
		try {
			employeeService.modify(id, employee);
			return YiwuJson.createBySuccess();
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
		
	}
	
	@ResponseBody
	@DeleteMapping(value="/{id}")
	public YiwuJson<?> delete(@PathVariable(name="id") Integer id){
		employeeService.delete(id);
		return YiwuJson.createBySuccess();
	}
	
}
