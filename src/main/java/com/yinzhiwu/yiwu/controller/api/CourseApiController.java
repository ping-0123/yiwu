package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.service.CourseYzwService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/course")
public class CourseApiController extends BaseController {

	@Autowired
	private CourseYzwService courseYzwService;

	@GetMapping("/{courseId}/lessons")
	@ApiOperation(value="查询{courseId}下的课时")
	public YiwuJson<PageBean<LessonApiView>> findPageOfLessonApiViewsOfCourse(
			@PathVariable(value="courseId", required = true) String courseId, 
			@RequestParam(value="pageNo", required=false, defaultValue="1") int pageNo,
			@RequestParam(value="pageSize", required=false, defaultValue="10") int pageSize)
	{
		if(courseId== null || "".equals(courseId))
			return YiwuJson.createByErrorMessage("courseId不能为空");
		CourseYzw course = courseYzwService.get(courseId);
		if(course == null || !StringUtils.hasText(course.getId()))
			return YiwuJson.createByErrorMessage("不存在id为\"" + courseId + "\"的课程");
		PageBean<LessonYzw> page1 = new PageBean<>(pageSize, pageNo, course.getLessons());
		//dao转dto
		List<LessonApiView> views = new ArrayList<>();
		for (LessonYzw lesson :  page1.getData()) {
			views.add(new LessonApiView(lesson));
		}
		
		PageBean<LessonApiView> page2 = new PageBean<>(pageSize, pageNo, course.getLessons().size(), views);
		return YiwuJson.createBySuccess(page2);
		
	}

	@GetMapping("/{id}")
	public YiwuJson<CourseVO> doGet(@PathVariable(name="id") String id) {
		
		try {
			CourseYzw course =  courseYzwService.get(id);
			return YiwuJson.createBySuccess(CourseVO.fromDAO(course));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getLocalizedMessage());
		}
	}


	@GetMapping("/connotation/{courseId}")
	@ApiOperation(value = "根据课程（课时系列）id获取课程内涵信息")
	public YiwuJson<Connotation> getConnotationByCourseId(@PathVariable String courseId) {
		try {
			CourseYzw course = courseYzwService.get(courseId);
			if (course == null)
				throw new Exception("未能找到couserId为" + courseId + "的课程");
			return new YiwuJson<>(course.getConnotation());
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	
}
