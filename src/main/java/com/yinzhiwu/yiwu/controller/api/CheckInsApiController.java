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
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.model.view.LessonCheckInSuccessApiView;
import com.yinzhiwu.yiwu.service.LessonCheckinService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * @deprecated since v2.1.4
 * @see {@link LessonCheckInsApiController}
 * @author ping
 * @Date 2017年10月29日 下午6:21:38
 *
 */

@Deprecated
@RestController
@RequestMapping("/api/checkIns")
@Api(value = "签到APIs 即将弃用 请使用/api/lessonCheckIns")
public class CheckInsApiController extends BaseController {

	@Autowired
	private LessonCheckinService checkInsYzwService;

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
			@RequestParam(value="pageNo", defaultValue = "1") int pageNo,
			@ApiParam(value = "pageSize should be positive", required = false) 
			@RequestParam(value="pageSize", defaultValue = "10") int pageSize) 
	{
		try {
			PageBean<LessonApiView> page = checkInsYzwService.findPageViewByCustomer(customerId, pageNo, pageSize);
			return YiwuJson.createBySuccess(page);
		} catch (Exception e) {
			logger.error(e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}

	}

	/**
	 * @see {@link LessonCheckinApiController#doCheckIn(Integer )}
	 * @param distributerId
	 * @param lessonId
	 * @return
	 */
	@Deprecated
	@PostMapping
	@ApiOperation(value = "学员签到, 已放弃,请使用接口/api/lessonCheckIns Post")
	public YiwuJson<LessonCheckInSuccessApiView> saveCustomerCheckIn(int distributerId, int lessonId) {
		try {
			LessonCheckInSuccessApiView view = checkInsYzwService.saveCustomerCheckIn(distributerId, lessonId);
			if(view != null)
				return YiwuJson.createBySuccess(view);
			else
				return YiwuJson.createBySuccessMessage("签到已成功,但由于数据一致性问题， 导致不能返回签到数据");
		} catch (Exception e) {
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
}
