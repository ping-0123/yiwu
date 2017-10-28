package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.business.LessonAppointmentException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.LessonAppointmentSuccessVO;
import com.yinzhiwu.yiwu.model.view.LessonAppointmentSuccessVO.LessonAppointmentSuccessVOConverter;
import com.yinzhiwu.yiwu.service.LessonAppointmentYzwService;
import com.yinzhiwu.yiwu.service.LessonYzwService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
*@Author ping
*@Time  创建时间:2017年10月28日下午1:56:22
*
*/

@RestController
@RequestMapping(value="/api/lessonAppointments")
@Api(description="课程预约APIs")
public class LessonAppointmentApiController extends BaseController {

		@Autowired LessonAppointmentYzwService lessonAppointmentService;
		@Autowired LessonYzwService lessonService;
		
		@PostMapping
		@ApiOperation(value="预约课时lessonId")
		public YiwuJson<LessonAppointmentSuccessVO> doAppoint(
				@RequestParam(required=true )Integer lessonId) throws DataNotFoundException, LessonAppointmentException
		{
			Distributer distributer = UserContext.getDistributer();
			LessonYzw lesson = lessonService.get(lessonId);
			LessonAppointmentYzw appointment  = lessonAppointmentService.doAppoint(distributer, lesson);
			
			//TODO
			return YiwuJson.createBySuccess(LessonAppointmentSuccessVOConverter.INSTANCE.fromPO(appointment));
		}
}
