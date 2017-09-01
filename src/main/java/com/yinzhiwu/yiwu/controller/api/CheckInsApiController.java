package com.yinzhiwu.yiwu.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.CheckInSuccessApiView;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.service.CheckInsYzwService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/checkIns")
@Api(value = "check-in")
public class CheckInsApiController extends BaseController {

	@Autowired
	private CheckInsYzwService checkInsYzwService;

	@GetMapping("/lesson/count")
	@ApiOperation(value = "获取学员已上课总节数")
	public YiwuJson<?> findCountByCustomerId(
			@ApiParam(value = "id of the customer", required = true) int customerId) {
		try {
			int count = checkInsYzwService.findCheckedInLessonsCountOfCustomer(customerId);
			return YiwuJson.createBySuccess(Integer.valueOf(count));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}

	@GetMapping("/lesson/list")
	@ApiOperation(value = "获取学员已上课课程列表")
	public YiwuJson<List<LessonApiView>> findByCustomerId(
			@ApiParam(value = "id of the customer", required = true) int customerId) {
		try {
			return checkInsYzwService.findByCustomerId(customerId);
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@GetMapping("/lesson/pageList")
	@ApiOperation(value = "分页获取学员已上课课程列表")
	public YiwuJson<PageBean<LessonApiView>> findPage(
			@ApiParam(value = "id of the customer", required = true)
			int customerId,
			@ApiParam(value = "pageNo should be positive", required = false)
			@RequestParam(value="pageNo", defaultValue = "1") 
			int pageNo,
			@ApiParam(value = "pageSize should be positive", required = false) 
			@RequestParam(value="pageSize", defaultValue = "10") 
			int pageSize) 
	{
		try {
			PageBean<LessonApiView> page = checkInsYzwService.findPageViewByCustomer(customerId, pageNo, pageSize);
			return YiwuJson.createBySuccess(page);
		} catch (Exception e) {
			logger.error(e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}

	}

	@PostMapping
	@ApiOperation(value = "学员签到")
	public YiwuJson<CheckInSuccessApiView> saveCustomerCheckIn(int distribuerId, int lessonId) {
		try {
			CheckInSuccessApiView view = checkInsYzwService.saveCustomerCheckIn(distribuerId, lessonId);
			if(view != null)
				return YiwuJson.createBySuccess(view);
			else
				return YiwuJson.createBySuccessMessage("签到已成功,但由于数据一致性问题， 导致不能返回签到数据");
		} catch (Exception e) {
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
}
