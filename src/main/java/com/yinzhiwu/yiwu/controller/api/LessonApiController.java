package com.yinzhiwu.yiwu.controller.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.Connotation;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.entity.yzwOld.Lesson;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.model.view.PrivateLessonApiView;
import com.yinzhiwu.yiwu.service.LessonService;
import com.yinzhiwu.yiwu.service.LessonYzwService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @since 用formater and validate
 * @author ping
 * @date 2017-03-20
 */

@CrossOrigin(value="*")
@RestController
@RequestMapping(value = "api/lesson")
public class LessonApiController extends BaseController {

	@Autowired
	@Qualifier(value = "lessonServiceImplTwo")
	private LessonService lessonService;

	@Autowired
	private LessonYzwService lessonYzwService;

	@Deprecated
	@RequestMapping(value = "/id/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Lesson getLesson(@PathVariable String id) {
		int intId = Integer.parseInt(id);
		return lessonService.findById(intId);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Lesson getLesson2(@PathVariable String id) {
		int intId = Integer.parseInt(id);
		return lessonService.findById(intId);
	}

	@GetMapping(value = "/connotation/{lessonId}")
	@ResponseBody
	@ApiOperation(value = "根据课时Id获取课时内涵信息")
	public YiwuJson<Connotation> getConnotationByLessonId(@PathVariable int lessonId) {
		try {
			LessonYzw lesson = lessonYzwService.get(lessonId);
			if (lesson == null)
				throw new Exception("未能找到lesson id为 " + lessonId + "的课时");
			return new YiwuJson<>(lesson.getConnotation());
		} catch (Exception e) {
			logger.error(e);
			return new YiwuJson<>(e.getMessage());
		}
	}

	@GetMapping(value = "/connotation/getLastNLessonsConnotation")
	@ResponseBody
	@ApiOperation(value = "获取本节课前N节课的课时内涵")
	public YiwuJson<Connotation> getLastNLessonsConnotation(
			@ApiParam(value = "本节课的课时Id", required = true) Integer thisLessonId,
			@ApiParam(value = "第前lastN节课, 为0表示返回本节课, 为负返回第后-lastN节课", required = true) Integer lastN) {
		try {
			Connotation connotation = lessonYzwService.getLastNLessonConnotation(thisLessonId, lastN);
			if (connotation != null)
				return new YiwuJson<>(connotation);
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
		return new YiwuJson<>("不存在前" + lastN + "课时");
	}

	@RequestMapping(value = "/weeklist",method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value="获取一周课表")
	public Object getLessonWeekList(
			 int storeId,  String courseType,
			 String teacherName,  String danceCatagory,  Date date,
			 String weChat) {
		
		if (date == null) {
			date = new Date();
		}
		return lessonService.findLessonWeekList(storeId, courseType, teacherName, danceCatagory, date, weChat);
	}

	@RequestMapping(value = "/arrangePriviteLesson", method = { RequestMethod.POST })
	public void arrangePriviteLesson(@ModelAttribute Lesson lesson) {
		lessonService.save(lesson);
	}

	@ResponseBody
	@GetMapping(value = "/list")
	@ApiOperation("根据课程Id(courseId)获取课时列表(lesson list)")
	public YiwuJson<List<LessonApiView>> findByCourseId(@ApiParam(value = "课程Id", required = true) String courseId) 
	{
		return lessonYzwService.findByCourseId(courseId);
	}

	@ResponseBody
	@GetMapping(value = "/listFaster")
	@ApiOperation("根据课程Id(courseId)获取课时列表(lesson list), 此API查询速度很快")
	public YiwuJson<List<LessonApiView>> findApiViewByCourseId(
			@ApiParam(value = "课程Id", required = true) String courseId) {
		try {
			List<LessonApiView> views = lessonYzwService.findApiViewByCourseId(courseId);
			return new YiwuJson<>(views);
		} catch (Exception e) {
			logger.error(e);
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@ResponseBody
	@GetMapping(value = "/courseType/private")
	@ApiOperation("根据会籍合约获取私教课课程列表")
	public YiwuJson<List<PrivateLessonApiView>> findByContractNo(String contractNo)
	{
		return lessonYzwService.findPrivateLessonApiViewsByContracNo(contractNo);
	}
	
	
	
	@ResponseBody
	@GetMapping(value="/courseType_closed")
	@ApiOperation("获取当天的封闭式课程列表, 不含舞种等级， 舞种等级描述\n")
	public YiwuJson<PageBean<LessonApiView>> findLessonApiViews(
			@ApiParam(required=true) Integer storeId,
			@ApiParam(required=false, defaultValue="today")Date date,
			@ApiParam(required=false, defaultValue="1") @RequestParam(defaultValue="1") int pageNo,
			@ApiParam(required=false, defaultValue="10") @RequestParam(defaultValue="10") int pageSize)

	{
		if(date == null)
			date = new Date();
		return lessonYzwService.findPageOfClosedLessonApiViewByStoreIdAndLessonDate(storeId, date, pageNo, pageSize);
	}
	
}
