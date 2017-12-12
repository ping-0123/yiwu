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
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.sys.Role;
import com.yinzhiwu.yiwu.entity.yzw.PostYzw;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.view.RoleZtreeApiView;
import com.yinzhiwu.yiwu.service.PostYzwService;
import com.yinzhiwu.yiwu.service.RoleService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;

/**
 * 
 * @author ping
 * @date 2017年9月19日下午8:24:05
 *
 */
@Controller
@RequestMapping(value="/system/posts")
public class PostController extends BaseController {

	@Autowired private PostYzwService postService;
	@Autowired private RoleService roleService;
	
	@GetMapping
	public String index(){
		return "redirect:posts/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model){
		return "posts/list";
	}
	
	
	@GetMapping("/form")
	public String showEditionForm(Model model){
		PostYzw post = new PostYzw();
		model.addAttribute("post", post);
		return "posts/createForm";
	}
	
	@GetMapping("/{id}/form")
	public String showModifyForm(@PathVariable(name="id") Integer id, Model model) throws DataNotFoundException{
		PostYzw post = postService.get(id);
		model.addAttribute("post", post);
		return "posts/updateForm";
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public YiwuJson<PostYzw> get(@PathVariable(name="id") Integer id) throws DataNotFoundException{
		return YiwuJson.createBySuccess(postService.get(id));
	}
	
	@GetMapping("/{id}/roleZtree")
	@ResponseBody
	 public YiwuJson<?> getRoleZtree(@PathVariable(name="id") Integer id){
    	try {
			PostYzw post = postService.get(id);
			Set<Role> hasRoles = post.getRoles();
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
    
	
	@PostMapping
	@ResponseBody
	public YiwuJson<PostYzw> createNewPost(@Valid PostYzw post, BindingResult bindingResult){
		if(bindingResult.hasErrors() ){	
			return YiwuJson.createByError();
		}
		postService.save(post);
		return YiwuJson.createBySuccess(post);
	}
	
	@PutMapping(value ="/{id}")
	@ResponseBody
	public YiwuJson<PostYzw> modify(@PathVariable(name="id") Integer id, PostYzw post){
		try {
			postService.modify(id, post);
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			logger.error(e.getMessage(), e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
		
		return YiwuJson.createBySuccess();
	}
	
	@PutMapping(value="/{id}/roles")
    @ResponseBody
    public YiwuJson<?> updateUserRoles(@PathVariable(name="id") Integer id, Integer[] roleIds){
    	try {
			PostYzw post = postService.get(id);
			Set<Role> roles = new HashSet<>();
			if(roleIds!=null && roleIds.length>0)
				for (Integer roleId : roleIds) {
					roles.add(roleService.get(roleId));
				}
			post.setRoles(roles);
			postService.update(post);
			
			return YiwuJson.createBySuccess();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
    }
	
	@ResponseBody
	@DeleteMapping(value="/{id}")
	public YiwuJson<?> delete(@PathVariable(name="id") Integer id){
		postService.delete(id);
		return YiwuJson.createBySuccess();
	}
	
	
	@ResponseBody
	@PostMapping(value="/datatable")
	public DataTableBean<?> getDatatable(HttpServletRequest request){
		try {
			QueryParameter para = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
			return postService.findDataTable(para);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		return null;
	}
	
}
