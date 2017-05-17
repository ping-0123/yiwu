package com.yinzhiwu.springmvc3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.model.TweetModel;
import com.yinzhiwu.springmvc3.service.TweetService;

@RestController
@RequestMapping(value="/api/tweet")
public class TweetController {

	@Autowired
	private TweetService tweetService;
	
	@PostMapping("/testSave")
	public int testSave(@Valid TweetModel m, BindingResult bindingResult){
		System.out.println(m.getContent());
		return tweetService.save(m);
	}
	
	
}
