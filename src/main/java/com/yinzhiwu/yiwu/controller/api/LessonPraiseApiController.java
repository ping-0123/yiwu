package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.LessonPraise;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.LessonPraiseService;
import com.yinzhiwu.yiwu.service.LessonYzwService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*@Author ping
*@Time  创建时间:2017年10月10日下午3:31:25
*
*/

@RestController
@RequestMapping(value="/api/lessonPraises")
@Api(value="课时点赞模块")
public class LessonPraiseApiController extends BaseController {
	
	@Autowired private LessonPraiseService lpService;
	@Autowired private LessonYzwService lessonService;
	
	@PostMapping(value="/doPraise.do")
	@ApiOperation(value = "点赞")
	public YiwuJson<?> doPost(
			@ApiParam(value="课时Id", required=true) Integer lessonId
	){
		
		try {
			Distributer distributer = UserContext.getDistributer();
			LessonYzw lesson = lessonService.get(lessonId);
			
			LessonPraise lp = new LessonPraise();
			lp.setDistributer(distributer);
			lp.setLesson(lesson);
			
			lpService.doLessonPraise(lp);
			return YiwuJson.createBySuccess();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
	
	@PostMapping(value="/cancelPraise.do")
	@ApiOperation(value="取消点赞")
	public YiwuJson<?> cancelPraise(
			@ApiParam(value="课时Id", required=true) Integer lessonId
	){
			
			//TODO 
			Distributer distributer = UserContext.getDistributer();
			LessonPraise lp;
			try {
				lp = lpService.findByDistributerIdAndLessonId(distributer.getId(), lessonId);
			} catch (DataNotFoundException e) {
				throw new RuntimeException("未点赞,不能取消点赞", e);
			}
			lp.setCanceled(true);
			lpService.cancelLessonPraise(lp);
			return YiwuJson.createBySuccess();
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value="删除点赞记录")
	public YiwuJson<?> delete(@PathVariable(name="id") Integer id){
		
		try{
			lpService.delete(id);
			return YiwuJson.createBySuccess();
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
}
