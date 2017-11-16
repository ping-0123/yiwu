package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
import com.yinzhiwu.yiwu.model.view.LessonApiView.LessonApiViewConverter;
import com.yinzhiwu.yiwu.model.view.LessonCheckInSuccessApiView.LessonCheckInSuccessApiViewConverter;
import com.yinzhiwu.yiwu.service.LessonCheckinService;
import com.yinzhiwu.yiwu.service.LessonYzwService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author ping
 * @Date 2017年10月29日 下午6:20:30
 *
 */

@RestController
@RequestMapping("/api/lessonCheckins")
@Api(description="课程签到APIs")
public class LessonCheckinApiController extends BaseController{

	@Autowired private LessonCheckinService lessonCheckInService;
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
	@ApiOperation(value="获取已上课列表 (/api/lessonCheckins/lessons) 和根据合约查询已上课的课程 (/api/lessonCheckins/lessons?contractNo={contractNo})")
	public YiwuJson<PageBean<LessonApiView>> findPage(
			@ApiParam(value="会籍合约号", required=false) String contractNo, 
			@RequestParam(value="pageNo", defaultValue = "1", required=false) Integer pageNo,
			@RequestParam(value="pageSize", defaultValue = "10", required=false) Integer pageSize)
	{
				Distributer distributer = UserContext.getDistributer();
				if(!StringUtils.hasLength(contractNo)){
					PageBean<LessonApiView> page = lessonCheckInService.findPageViewByCustomer(
							distributer.getCustomer().getId(), pageNo, pageSize);
					return YiwuJson.createBySuccess(page);
				}else{
					PageBean<LessonCheckInYzw> page = lessonCheckInService.findPageByProperty("contractNo", contractNo, pageNo, pageSize);
					List<LessonApiView> views = new ArrayList<>();
					for(LessonCheckInYzw checkin : page.getList()){
						views.add(LessonApiViewConverter.INSTANCE.fromPO(checkin.getLesson()));
					}
					
					return YiwuJson.createBySuccess(new PageBean<>(page.getPageSize(),page.getCurrentPage()
							,page.getTotalRecord(),views));
				}
				
	}
	
}
