package com.yinzhiwu.yiwu.controller.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.OneDayLessonsVO;
import com.yinzhiwu.yiwu.service.LessonYzwService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*@Author ping
*@Time  创建时间:2017年11月24日下午1:35:51
*
*/

@RestController
@RequestMapping("/api/temp")
public class TempApiController {
	
	@Autowired  private LessonYzwService lessonService;
	
	
	@GetMapping(value = "/weekLessons")
	@ApiOperation(value="获取一周课表, 不需要通过token验证")
	public YiwuJson<List<OneDayLessonsVO>> getLessonWeekList(
			 @ApiParam(value="门店Id,默认为所有门店", required=false) Integer storeId, 
			 @ApiParam(value="课程类型，取值{CLOSED,OPENED,PRIVATE} 默认所有类型",required=false) CourseType courseType,
			 @ApiParam(value="教师姓名", required=false) String teacherName,  
			 @ApiParam(value="舞种", required=false) String danceCatagory, 
			 @ApiParam(value="日期  默认为当天", required=false) Date date) 
	{
		
		if (date == null) {
			date = new Date();
		}
		List<OneDayLessonsVO> list =  lessonService.findWeeklyLessons(storeId, courseType, teacherName, danceCatagory, date); 
		return YiwuJson.createBySuccess(list);
	}
}
