package com.yinzhiwu.yiwu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.entity.LessonComment;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.LessonCommentVO;
import com.yinzhiwu.yiwu.model.view.LessonCommentVO.LessonCommentVOConverter;
import com.yinzhiwu.yiwu.service.LessonCommentService;
import com.yinzhiwu.yiwu.util.beanutils.Converter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
*@Author ping
*@Time  创建时间:2017年10月10日下午5:47:30
*
*/

@RestController
@RequestMapping(value="/api/lessonComments")
@Api(value="Lesson Appraise 课程评论模块", tags={"CLOSED"})
public class LessonCommentApiController extends BaseController {
	
	@Autowired LessonCommentService laService;
	
	@PostMapping
	@ApiOperation(value="评论课时")
	public YiwuJson<LessonCommentVO> doPost(@Valid LessonCommentVO commentVO, BindingResult result){
		if(result.hasErrors()){
			YiwuJson.createByErrorMessage(getErrorsMessage(result));
		}
		
		try {
			Converter<LessonComment,LessonCommentVO> converter = LessonCommentVOConverter.instance;
			LessonComment comment = converter.toPO(commentVO);
			comment.setCommenter(UserContext.getDistributer());
			laService.doLessonComment(comment);
			
			return YiwuJson.createBySuccess(converter.fromPO(comment));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
}
