package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.business.LessonCheckInException;
import com.yinzhiwu.yiwu.exception.business.LessonInteractiveException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.model.view.LessonCheckInSuccessApiView;
import com.yinzhiwu.yiwu.model.view.LessonCheckInSuccessApiView.LessonCheckInSuccessApiViewConverter;
import com.yinzhiwu.yiwu.service.LessonCheckInYzwService;
import com.yinzhiwu.yiwu.service.LessonYzwService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author ping
 * @Date 2017年10月29日 下午6:20:30
 *
 */

@RestController
@RequestMapping("/api/lessonCheckIns")
@Api(description="课程签到APIs")
public class LessonCheckInApiController extends BaseController{

	@Autowired private LessonCheckInYzwService lessonCheckInService;
	@Autowired private LessonYzwService lessonService;
	
	@PostMapping
	@ApiOperation(value="学员签到")
	public YiwuJson<LessonCheckInSuccessApiView> doCheckIn(
			@RequestParam(name="lessonId", required=true) Integer lessonId) throws DataNotFoundException, LessonCheckInException, LessonInteractiveException{
		
		Distributer distributer = UserContext.getDistributer();
		LessonYzw lesson = lessonService.get(lessonId);
		
		LessonCheckInYzw checkIn = lessonCheckInService.doCheckIn(distributer,lesson);
		
		return YiwuJson.createBySuccess(
				LessonCheckInSuccessApiViewConverter.INSTANCE.fromPO(checkIn));
	}
	
	@GetMapping("lessons/count")
	@ApiOperation(value="获取用户已上课总节数")
	public YiwuJson<Integer> getCountOfCheckedInLessons(){
		Distributer distributer = UserContext.getDistributer();
		Integer count = lessonCheckInService.findCountByCustomerId(distributer.getCustomer().getId());
		return YiwuJson.createBySuccess(count);
	}
	
	@GetMapping("/lessons")
	@ApiOperation(value="获取已上课列表")
	public YiwuJson<PageBean<LessonApiView>> findPage(
			@RequestParam(value="pageNo", defaultValue = "1", required=false) Integer pageNo,
			@RequestParam(value="pageSize", defaultValue = "10", required=false) Integer pageSize)
	{
				Distributer distributer = UserContext.getDistributer();
				PageBean<LessonApiView> page = lessonCheckInService.findPageViewByCustomer(
						distributer.getCustomer().getId(), pageNo, pageSize);
				return YiwuJson.createBySuccess(page);
	}
	
}
