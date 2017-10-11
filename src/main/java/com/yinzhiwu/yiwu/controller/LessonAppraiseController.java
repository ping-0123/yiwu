package com.yinzhiwu.yiwu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.entity.LessonAppraise;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.LessonAppraiseVO;
import com.yinzhiwu.yiwu.service.LessonAppraiseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
*@Author ping
*@Time  创建时间:2017年10月10日下午5:47:30
*
*/

@RestController
@RequestMapping(value="/api/appraises")
@Api(value="Lesson Appraise 课程评论模块", tags={"CLOSED"})
public class LessonAppraiseController extends BaseController {
	
	@Autowired LessonAppraiseService laService;
	
	@PostMapping
	@ApiOperation(value="评论课时")
	public YiwuJson<?> doPost(@Valid LessonAppraiseVO appraiseVO, BindingResult result){
		if(result.hasErrors()){
			YiwuJson.createByErrorMessage(getErrorsMessage(result));
		}
		
		try {
			LessonAppraise appraise = LessonAppraiseVO.toPO(appraiseVO);
			laService.save(appraise);
			
			return YiwuJson.createBySuccess();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
}
