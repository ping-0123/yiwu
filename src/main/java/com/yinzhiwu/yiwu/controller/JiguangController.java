package com.yinzhiwu.yiwu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jiguang")
public class JiguangController {
	
	@GetMapping("/verify")
	public String verify(@RequestParam String echostr ){
		return echostr;
	}

}
