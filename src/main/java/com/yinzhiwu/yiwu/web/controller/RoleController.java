package com.yinzhiwu.yiwu.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.sys.Role;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.RoleService;

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

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
    	return "redirect:roles/list";
    }

    @GetMapping(value="list")
    public String list(Model model){
    	 model.addAttribute("roles", roleService.findAll());
         return "roles/list";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("role", new Role());
        return "roles/createForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Role role, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		logger.error(getErrorsMessage(bindingResult));
    	}
    	
        roleService.save(role);
        
        return "redirect:roles/list";
    }

    @RequestMapping(value = "/{id}/form", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable(name="id") Integer id, Model model) {
        model.addAttribute("role", roleService.get(id));
        
        return "roles/editForm";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String update(@Valid Role role, @PathVariable(name="id") Integer id, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		logger.error(getErrorsMessage(bindingResult));
    	}
    	
        try {
			roleService.modify(id, role);
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			logger.error(e.getMessage(),e);
		}
        return "redirect:list";
    }


    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public YiwuJson<?> delete(@PathVariable(name="id") Integer id) {
        roleService.delete(id);
//        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return YiwuJson.createBySuccess();
    }


}
