package com.yinzhiwu.yiwu.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.entity.type.TweetType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.TweetTypeService;

@RestController
@RequestMapping(value="/api/types/tweetType")
public class TweetTypeApiController {

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
