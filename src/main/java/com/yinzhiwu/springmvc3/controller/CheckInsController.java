package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;
import com.yinzhiwu.springmvc3.service.CheckInsYzwService;

@RestController
@RequestMapping("/api/checkIns")
public class CheckInsController {

	@Autowired
	private CheckInsYzwService checkInsYzwService;
	
	@GetMapping("/lesson/count")
	public YiwuJson<Integer> findCountByCustomerId(int customerId){
		return checkInsYzwService.findCountByCustomerId(customerId);
	}
	
	@GetMapping("/lesson/list")
	public YiwuJson<List<LessonApiView>> findByCustomerId(int customerId){
		return checkInsYzwService.findByCustomerId(customerId);
	}
}
