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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/checkIns")
@Api(value="check-in")
public class CheckInsController extends BaseController{

	@Autowired private CheckInsYzwService checkInsYzwService;
	@Autowired private CustomerYzwService customerService;
	@Autowired private LessonYzwService lessonService;
	
	
	@GetMapping("/lesson/count")
	@ApiOperation(value="获取学员已上课总节数")
	public YiwuJson<Integer> findCountByCustomerId(
			@ApiParam(value="id of the customer", required =true) int customerId)
	{
		try{
			return new YiwuJson<>(new Integer(checkInsYzwService.findCountByCustomerId(customerId))) ;
		}catch (Exception e) {
			logger.error(e.getMessage());
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@GetMapping("/lesson/list")
	@ApiOperation(value="获取学员已上课课程列表")
	public YiwuJson<List<LessonApiView>> findByCustomerId(
			@ApiParam(value="id of the customer", required =true) int customerId)
	{
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
