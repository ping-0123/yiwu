package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CheckInSuccessApiView;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;
import com.yinzhiwu.springmvc3.service.CheckInsYzwService;
import com.yinzhiwu.springmvc3.service.CustomerYzwService;
import com.yinzhiwu.springmvc3.service.DistributerService;
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
	@Autowired private DistributerService distributerService;
	
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
	@ApiOperation(value="学员签到")
	public YiwuJson<CheckInSuccessApiView> saveCustomerCheckIn(int distribuerId, int lessonId){
		try {
			Distributer distributer = distributerService.get(distribuerId);
			if(distributer == null) throw new Exception(distribuerId + "用户不存在");
			LessonYzw lesson = lessonService.get(lessonId);
			if(lesson == null ) throw new Exception(lessonId + "课时不存在");
			return new YiwuJson<>(checkInsYzwService.saveCustomerCheckIn(distributer,lesson));
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
}
