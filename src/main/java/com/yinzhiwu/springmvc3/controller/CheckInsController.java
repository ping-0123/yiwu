package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CheckInSuccessApiView;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;
import com.yinzhiwu.springmvc3.service.CheckInsYzwService;
import com.yinzhiwu.springmvc3.service.CustomerYzwService;
import com.yinzhiwu.springmvc3.service.LessonYzwService;

@RestController
@RequestMapping("/api/checkIns")
public class CheckInsController extends BaseController{

	@Autowired private CheckInsYzwService checkInsYzwService;
	@Autowired private CustomerYzwService customerService;
	@Autowired private LessonYzwService lessonService;
	
	
	@GetMapping("/lesson/count")
	public YiwuJson<Integer> findCountByCustomerId(int customerId){
		try{
			return new YiwuJson<>(new Integer(checkInsYzwService.findCountByCustomerId(customerId))) ;
		}catch (Exception e) {
			logger.error(e.getMessage());
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@GetMapping("/lesson/list")
	public YiwuJson<List<LessonApiView>> findByCustomerId(int customerId){
		return checkInsYzwService.findByCustomerId(customerId);
	}
	
	@PostMapping
	public YiwuJson<CheckInSuccessApiView> saveCustomerCheckIn(int customerId, int lessonId){
		try {
			CustomerYzw customer = customerService.get(customerId);
			LessonYzw lesson = lessonService.get(lessonId);
			return new YiwuJson<>(checkInsYzwService.saveCustomerCheckIn(customer,lesson));
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
}
