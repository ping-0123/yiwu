package com.yinzhiwu.yiwu.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.PostYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.PostYzwService;

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
	
	@GetMapping
	public String index(){
		return "redirect:posts/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model){
		List<PostYzw> posts = postService.findAll();
		model.addAttribute("posts", posts);
		return "posts/list";
	}
	
	@PostMapping
	public String createNewPost(PostYzw post){
		postService.save(post);
		return "redirect:posts/list";
	}
	
	@GetMapping("/form")
	public String showEditionForm(Model model){
		PostYzw post = new PostYzw();
		model.addAttribute("post", post);
		return "posts/formCreate";
	}
	
	@GetMapping("/{id}/form")
	public String showModifyForm(@PathVariable(name="id") Integer id, Model model){
		PostYzw post = postService.get(id);
		model.addAttribute("post", post);
		return "posts/formEdit";
	}
	
	@PutMapping(value ="/{id}")
	public String modify(@PathVariable(name="id") Integer id, PostYzw post){
		try {
			postService.modify(id, post);
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		
		return "redirect:list";
	}
}
