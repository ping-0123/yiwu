package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.LessonInteractiveVO;
import com.yinzhiwu.yiwu.model.view.LessonInteractiveVO.LessonInteractiveVOConverter;
import com.yinzhiwu.yiwu.service.LessonInteractiveService;
import com.yinzhiwu.yiwu.service.LessonYzwService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
*@Author ping
*@Time  创建时间:2017年10月28日上午9:36:38
*
*/


@RestController
@RequestMapping(value="/api/lessonInteractives")
@Api(description="用户课时交互APIs")
class LessonInteractiveApiController extends BaseController {
	
	@Autowired private LessonInteractiveService lessonInteractiveService;
	@Autowired private LessonYzwService lessonService;
	
	@GetMapping
	@ApiOperation(value="获取课时id与当前用户的交互结果")
	public YiwuJson<LessonInteractiveVO> doGet(
			@RequestParam(name="lessonId", required =true) Integer lessonId)
	{
		Distributer distributer = UserContext.getDistributer();
		LessonInteractive interactive;
		
		try {
			interactive = lessonInteractiveService.findByDistributerIdAndLessonId(distributer.getId(), lessonId);
		} catch (DataNotFoundException e) {
			interactive = new LessonInteractive();
			LessonYzw lesson;
			try {
				lesson = lessonService.get(lessonId);
			} catch (DataNotFoundException e1) {
				return YiwuJson.createByErrorMessage("请传入正确的课时Id," + e1.getMessage() );
			}
			interactive.setDistributer(distributer);
			interactive.setLesson(lesson);
			lessonInteractiveService.save(interactive);
		}
		
		return YiwuJson.createBySuccess(LessonInteractiveVOConverter.INSTANCE.fromPO(interactive));
	}
}
