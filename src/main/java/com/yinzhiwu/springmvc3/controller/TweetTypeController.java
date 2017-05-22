package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.TweetTypeApiView;
import com.yinzhiwu.springmvc3.service.TweetTypeService;

@RestController
@RequestMapping(value="/api/tweetType")
public class TweetTypeController {

	@Autowired
	private TweetTypeService tweetTypeService;
	
	@GetMapping(value="/list")
	public YiwuJson<List<TweetTypeApiView>> findAll(){
		return tweetTypeService.findAllTweetTypes();
	}
}
