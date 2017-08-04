package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.AppointSuccessApiView;
import com.yinzhiwu.yiwu.service.AppointmentEventService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author ping
 */

@RestController
@RequestMapping("/api/appointment")
public class AppointmentApiController extends BaseController {

	@Autowired
	private AppointmentEventService appointmentEventService;

	@PostMapping(value = "/appoint")
	@ApiOperation(value = "预约课程")
	public YiwuJson<AppointSuccessApiView> appointLesson(
			@ApiParam(required = true) int distributerId,
			@ApiParam(required = true) int lessonId) {
		try {
			return new YiwuJson<>(appointmentEventService.saveAppoint(distributerId, lessonId));
		} catch (Exception e) {
//			e.printStackTrace();
			return new YiwuJson<>(e.getMessage());
		}
	}

	@PostMapping(value = "/unAppoint")
	@ApiOperation(value = "取消课程预约")
	public YiwuJson<AppointSuccessApiView> unAppointLesson(@ApiParam(required = true) int distributerId,
			@ApiParam(required = true) int lessonId) {
		try {
			return new YiwuJson<>(appointmentEventService.saveUnAppoint(distributerId, lessonId));
		} catch (Exception e) {
//			e.printStackTrace();
			return new YiwuJson<>(e.getMessage());
		}
	}
}