package com.yinzhiwu.yiwu.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @author ping
 * @Date 2017年9月17日 下午10:35:37
 *
 */

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.PostYzw;
import com.yinzhiwu.yiwu.service.PostYzwService;
@Controller
@RequestMapping(value="/system/posts")
public class PostController extends BaseController {

	@Autowired private PostYzwService postService;
	
	@GetMapping
	public String list(Model model){
		List<PostYzw> posts = postService.findAll();
		model.addAttribute("posts", posts);
		return "posts/list";
	}
}
