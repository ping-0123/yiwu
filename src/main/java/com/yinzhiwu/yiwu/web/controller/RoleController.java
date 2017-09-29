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
import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.entity.sys.Role;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.view.ResourceZTreeApiView;
import com.yinzhiwu.yiwu.service.ResourceService;
import com.yinzhiwu.yiwu.service.RoleService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;

/**
 * 
 * @author ping
 * @date 2017年9月15日下午3:57:30
 *
 */

@Controller
@RequestMapping("/system/roles")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;
    @Autowired private ResourceService resourceService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
    	return "redirect:roles/list";
    }

    @GetMapping(value="list")
    public String list(Model model){
//    	 model.addAttribute("roles", roleService.findAll());
         return "roles/list";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("role", new Role());
        return "roles/createForm";
    }
    
    @RequestMapping(value = "/{id}/form", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable(name="id") Integer id, Model model) {
    	model.addAttribute("role", roleService.get(id));
    	return "roles/updateForm";
    }

    @ResponseBody
    @GetMapping(value="/{id}")
    public YiwuJson<?> get(@PathVariable(name="id") Integer id){
    	
    	try {
    		return YiwuJson.createBySuccess(roleService.get(id));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
    }
    
    @ResponseBody
    @GetMapping(value="/{id}/resourceZTree")
    public YiwuJson<?> getResourceZTree(@PathVariable(name="id") Integer id){
    	try {
			Role role = roleService.get(id);
			Set<Resource> hasResources = role.getResources();
			List<Resource> allRes = resourceService.findAll();
    		List<ResourceZTreeApiView> view = new ArrayList<>();
			for (Resource resource : allRes) {
				boolean checked = false;
				if (hasResources.contains(resource))
					checked=true;
				view.add(new ResourceZTreeApiView(
						resource.getId(),
						resource.getParent()==null? null:resource.getParent().getId(), 
						resource.getName(),
						checked));
			}
			
			return YiwuJson.createBySuccess(view);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public YiwuJson<?> create(@Valid Role role, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		logger.error(getErrorsMessage(bindingResult));
    		return YiwuJson.createByErrorMessage(getErrorsMessage(bindingResult));
    	}
    	
    	try {
    		roleService.save(role);
    		return YiwuJson.createBySuccess(role);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
    }


    @PutMapping(value = "/{id}")
    @ResponseBody
    public YiwuJson<?> update(@Valid Role role, @PathVariable(name="id") Integer id, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		logger.error(getErrorsMessage(bindingResult));
    		return YiwuJson.createByErrorMessage(getErrorsMessage(bindingResult));
    	}
    	
        try {
			roleService.modify(id, role);
			return YiwuJson.createBySuccess();
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
        
    }

    @PutMapping(value="/{id}/resources")
    @ResponseBody
    public YiwuJson<?> updateRoleResources(@PathVariable(name="id") Integer id, Integer[] resourceIds){
    	try {
			Role role = roleService.get(id);
			Set<Resource> res = new HashSet<>();
			if(resourceIds!=null && resourceIds.length> 0)
				for (Integer resId : resourceIds) {
					res.add(resourceService.get(resId));
				}
    		role.setResources(res);
			roleService.update(role);
			
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
    		roleService.delete(id);
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
			return roleService.findDataTable(parameter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
				| InstantiationException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		return null;
	}
}
