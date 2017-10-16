package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.Connotation;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.CourseVO;
import com.yinzhiwu.yiwu.model.view.LessonVO;
import com.yinzhiwu.yiwu.service.CourseYzwService;
import com.yinzhiwu.yiwu.service.LessonYzwService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/course")
public class CourseApiController extends BaseController {

	@Autowired private CourseYzwService courseService;
	@Autowired private LessonYzwService lessonService;

	@GetMapping("/{id}/lessons")
	@ApiOperation(value="查询课程{id}下的课时: /{id}/lessons?pageSize=1&ordinalNo=1, 查询第一节课")
	public YiwuJson<PageBean<LessonVO>> findLessons(
			@PathVariable(value="id", required = true) String id, 
			@RequestParam(value="pageNo", required=false, defaultValue="1") int pageNo,
			@RequestParam(value="pageSize", required=false, defaultValue="10") int pageSize,
			LessonVO search)
	{
		if(search==null){
			search = new LessonVO();
		}
		search.setCourseId(id);
		LessonYzw lesson  = search.toPO();
		PageBean<LessonYzw> page = lessonService.findPageByExample(lesson, pageNo, pageSize);
		if(page.getData()==null || page.getData().size()==0)
			return YiwuJson.createByErrorMessage("没有查到任何数据");
		List<LessonVO> vos = new ArrayList<>();
		for(LessonYzw lesson1: page.getData()){
			vos.add(new LessonVO().fromPO(lesson1));
		}
		
		return YiwuJson.createBySuccess(
				new PageBean<>(pageSize, pageNo, page.getTotalRecord(), vos));
	}
	
	@GetMapping("/{id}")
	public YiwuJson<CourseVO> doGet(@PathVariable(name="id") String id) {
		
		try {
			CourseYzw course =  courseService.get(id);
			return YiwuJson.createBySuccess(new CourseVO().fromPO(course));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getLocalizedMessage());
		}
	}
	
	
	@GetMapping("/{id}/nextLesson")
	@ApiOperation(value="获取下一个课时")
	public YiwuJson<LessonVO> getNextLesson(@PathVariable(name="id") String id){
		
		try {
			Date current = new Date();
			CourseYzw course = courseService.get(id);
			if(course.getEndDate().before(current)){
				throw new Exception("课程已结束");
			}
			List<LessonYzw> lessons = course.getLessons();
			for (LessonYzw lesson : lessons) {
				if(lesson.getStartDateTime().after(current)){
					return YiwuJson.createBySuccess(LessonVO.fromDAO(lesson));
				}
			}
			
			throw new Exception("课程已结束");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getLocalizedMessage());
		}
		
	}
	
	@GetMapping("/{id}/passedLessons")
	@ApiOperation(value="获取已上过的课时 ")
	public YiwuJson<PageBean<LessonVO>> getPageOfPassedLessons(
			@PathVariable(name="id") String id,
			@RequestParam(name="pageNo", required=false, defaultValue="1") Integer pageNo,
			@RequestParam(name="pageSize", required=false, defaultValue="10") Integer pageSize
	){
		try {
			CourseYzw course = courseService.get(id);
			List<LessonYzw> lessons = course.getPreviousLessons();
			PageBean<LessonYzw> page1 = new PageBean<>(pageSize,pageNo,lessons);
			List<LessonVO> vos = new ArrayList<>();
			for (LessonYzw lesson : page1.getList()) {
				vos.add(LessonVO.fromDAO(lesson));
			}
			
			PageBean<LessonVO> page2 = new PageBean<>(pageSize,pageNo,lessons.size(),vos);
			return YiwuJson.createBySuccess(page2);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getLocalizedMessage());
		}
	}
	
	
	@GetMapping("/{id}/nextLessons")
	@ApiOperation(value="获取未上课的课时列表")
	public YiwuJson<PageBean<LessonVO>> getPageOfNextLessons(
			@PathVariable(name="id") String id,
			@RequestParam(name="pageNo", required=false, defaultValue="1") Integer pageNo,
			@RequestParam(name="pageSize", required=false, defaultValue="10") Integer pageSize
	){
		
		try {
			CourseYzw course = courseService.get(id);
			List<LessonYzw> lessons = course.getNextLessons();
			PageBean<LessonYzw> page1 = new PageBean<>(pageSize,pageNo,lessons);
			List<LessonVO> vos = new ArrayList<>();
			for(LessonYzw lesson: page1.getList()){
				vos.add(LessonVO.fromDAO(lesson));
			}
			
			PageBean<LessonVO> page2  = new PageBean<>(pageSize,pageNo,lessons.size(), vos);
			return YiwuJson.createBySuccess(page2);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getLocalizedMessage());
		}
	}
	
	@GetMapping("/connotation/{courseId}")
	@ApiOperation(value = "根据课程（课时系列）id获取课程内涵信息")
	public YiwuJson<Connotation> getConnotationByCourseId(@PathVariable String courseId) {
		try {
			CourseYzw course = courseService.get(courseId);
			if (course == null)
				throw new Exception("未能找到couserId为" + courseId + "的课程");
			return new YiwuJson<>(course.getConnotation());
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	
}
