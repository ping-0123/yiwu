package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.entity.type.TweetType;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.TweetTypeService;

@RestController
@RequestMapping(value="/api/types/tweetType")
public class TweetTypeController {

	@Autowired
	private TweetTypeService tweetTypeService;
	
	@GetMapping(value="/list")
	public YiwuJson<List<TweetType>> doList(){
		try {
			List<TweetType> types = tweetTypeService.findAll();
			return new YiwuJson<>(types);
		} catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
}
