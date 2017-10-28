package com.yinzhiwu.yiwu.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.ResourceZTreeApiView;
import com.yinzhiwu.yiwu.service.ResourceService;


/**
 * 
 * @author ping
 * @date 2017年9月15日下午3:00:34
 *
 */

@Controller
@RequestMapping("/system/resources")
public class ResourceController extends BaseController {

    @Autowired private ResourceService service;

    @ModelAttribute("types")
    public Resource.ResourceType[] resourceTypes() {
        return Resource.ResourceType.values();
    }

    
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("resources", service.findAll());
        return "resources/list";
    }

    @ResponseBody
    @GetMapping(value="/ztree")
    private YiwuJson<?> getResourceZtree(){
    	try {
    		List<Resource> allRes = service.findAll();
    		List<ResourceZTreeApiView> view = new ArrayList<>();
			for (Resource resource : allRes) {
				view.add(new ResourceZTreeApiView(
						resource.getId(),
						resource.getParent()==null? null:resource.getParent().getId(), 
						resource.getName(),
						false));
			}
			
			return YiwuJson.createBySuccess(view);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
    }
    
    @GetMapping(value="/createForm")
    public String showCreateForm(Integer parentId, Model model) throws DataNotFoundException{
    	Resource parent = parentId==null?null:service.get(parentId);
    	model.addAttribute("parent",parent );
    	return "resources/createForm";
    }
    
    @GetMapping(value="/{id}/updateForm")
    public String showUpdateForm(@PathVariable(name="id") Integer id, Model model) throws DataNotFoundException{
    	model.addAttribute("resource", service.get(id));
    	return "resources/updateForm";
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
    
    
    @PostMapping
    @ResponseBody
    public YiwuJson<?> create(@Valid Resource resource, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		logger.error(getErrorsMessage(bindingResult));
    		return YiwuJson.createByErrorMessage(getErrorsMessage(bindingResult));
    	}
    	
    	try {
    		service.save(resource);
    		return YiwuJson.createBySuccess(resource);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
    }


    @PutMapping(value = "/{id}")
    @ResponseBody
    public YiwuJson<?> update(@Valid Resource resource, @PathVariable(name="id") Integer id, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		logger.error(getErrorsMessage(bindingResult));
    		return YiwuJson.createByErrorMessage(getErrorsMessage(bindingResult));
    	}
    	
        try {
			service.modify(id, resource);
			return YiwuJson.createBySuccess();
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
        
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public YiwuJson<?> delete(@PathVariable(name="id") Integer id) {
    	
    	try {
    		Resource r = service.get(id);
    		if(r.getChildren().size()>0)
    			return YiwuJson.createByErrorMessage("该资源下存在子资源,请先删除子资源");
    		service.delete(r);
    		return YiwuJson.createBySuccess();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
    }

}
