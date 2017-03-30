package com.yinzhiwu.springmvc3.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinzhiwu.springmvc3.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource(name="userServiceImplOne")
//	@Autowired
//	@Qualifier("two")
	private UserService userService;
	
	@RequestMapping("/show")
	public String show(){
		System.out.println("userController called");
		userService.show();
		return "show";
	}
}
