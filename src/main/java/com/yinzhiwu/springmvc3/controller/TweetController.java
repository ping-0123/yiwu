package com.yinzhiwu.springmvc3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.model.TweetModel;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.TweetService;

@RestController
@RequestMapping(value="/api/tweet")
public class TweetController {

	@Autowired
	private TweetService tweetService;
	
	@PostMapping("/save")
	public YiwuJson<Boolean> save(@Valid TweetModel m, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return new YiwuJson<>(fieldError.getField() + ": " + fieldError.getDefaultMessage());
		}
		tweetService.save(m);
		return new YiwuJson<>(new Boolean(true));
	}
	
	
}
