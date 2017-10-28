package com.yinzhiwu.yiwu.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.sys.Role;
import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.enums.DataStatus;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.view.RoleZtreeApiView;
import com.yinzhiwu.yiwu.model.view.UserVO;
import com.yinzhiwu.yiwu.service.RoleService;
import com.yinzhiwu.yiwu.service.UserService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;


/**
 * 
 * @author ping
 * @date 2017年9月15日下午4:02:32
 *
 */

@Controller
@RequestMapping("/system/users")
public class UserController extends BaseController {

	
    @Autowired
    private UserService service;
    @Autowired private RoleService roleService;

    @GetMapping
    public String index(){
    	return "redirect:users/list";
    }
    
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(Model model) {
        return "users/list";
    }
    
    @GetMapping(value="/form")
    public String showCreateForm(Model model){
    	model.addAttribute("user", new User());
    	return "users/createForm";
    }
    
    @GetMapping(value="/{id}/form")
    public String showUpdateForm(@PathVariable(name="id") Integer id, Model model) throws DataNotFoundException{
    	model.addAttribute("user", service.get(id));
    	return "users/updateForm";
    }

    @ResponseBody
    @GetMapping(value="/{id}")
    public YiwuJson<?> get(@PathVariable(name="id") Integer id){
    	
    	try {
    		return YiwuJson.createBySuccess(service.get(id));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
    }
    
    @ResponseBody
    @GetMapping(value="/{id}/roleZtree")
    public YiwuJson<?> getRoleZtree(@PathVariable(name="id") Integer id){
    	try {
			User user = service.get(id);
			Set<Role> hasRoles = user.getRoles();
			List<Role> allRoles = roleService.findAll();
			List<RoleZtreeApiView> view = new ArrayList<>();
			for (Role role : allRoles) {
				boolean checked = false;
				if(hasRoles.contains(role))
					checked = true;
				view.add(new RoleZtreeApiView(role.getId(), role.getName(), checked));
			}
			
			return YiwuJson.createBySuccess(view);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public YiwuJson<?> create(@Valid User user, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		logger.error(getErrorsMessage(bindingResult));
    		return YiwuJson.createByErrorMessage(getErrorsMessage(bindingResult));
    	}
    	
    	try {
    		service.save(user);
    		return YiwuJson.createBySuccess(user);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
    }


    @PutMapping(value = "/{id}")
    @ResponseBody
    public YiwuJson<?> update(@Valid User user, @PathVariable(name="id") Integer id, BindingResult bindingResult) throws DataNotFoundException {
    	if(bindingResult.hasErrors()){
    		logger.error(getErrorsMessage(bindingResult));
    		return YiwuJson.createByErrorMessage(getErrorsMessage(bindingResult));
    	}
    	
        try {
        	User source = service.get(id);
        	if("Admin".equals(source.getUsername()) && DataStatus.NORMAL != user.getDataStatus())
        		throw new UnsupportedOperationException("超级管理员账号不能被禁用");
			service.modify(source, user);
			return YiwuJson.createBySuccess();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
        
    }

    @PutMapping(value="/{id}/roles")
    @ResponseBody
    public YiwuJson<?> updateUserRoles(@PathVariable(name="id") Integer id, Integer[] roleIds){
    	try {
			User user = service.get(id);
			Set<com.yinzhiwu.yiwu.entity.sys.Role> roles = new HashSet<>();
			if(roleIds!=null && roleIds.length>0)
				for (Integer roleId : roleIds) {
					roles.add(roleService.get(roleId));
				}
			user.setRoles(roles);
			service.update(user);
			
			return YiwuJson.createBySuccess();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
    }
  
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public YiwuJson<?> delete(@PathVariable(name="id") Integer id) {
    	
    	try {
    		User user = service.get(id);
    		if("Admin".equals(user.getUsername()))
    			throw new UnsupportedOperationException("不能删除超级管理员帐号");
    		service.delete(user);
    		return YiwuJson.createBySuccess();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
    }

	@PostMapping(value="/datatable")
	@ResponseBody
	public DataTableBean<?> findDatatable(HttpServletRequest request){
		
		try {
			QueryParameter parameter = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
			ServletRequestUtils.transferQueryParamter(parameter, UserVO.class);
			DataTableBean<User> dtb =  service.findDataTable(parameter);
			@SuppressWarnings("unchecked")
			List<User> users = (List<User>) dtb.getData();
			List<UserVO> vos = new ArrayList<>();
			for (User user : users) {
				vos.add(new UserVO().fromPO(user));
			}
			
			return new DataTableBean<>(
					dtb.getDraw(), dtb.getRecordsTotal(), dtb.getRecordsFiltered(), vos,dtb.getError());
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
				| InstantiationException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		return null;
	}
    
  }
