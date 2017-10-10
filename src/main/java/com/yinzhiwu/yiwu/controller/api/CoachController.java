package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.CoachVO;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;

import io.swagger.annotations.ApiOperation;

/**
*@Author ping
*@Time  创建时间:2017年10月7日下午3:56:28
*
*/

@RestController
@RequestMapping(value="/api/coaches")
public class CoachController extends BaseController {
	
	@Autowired private EmployeeYzwService empService;

	@GetMapping(value="/{id}")
	@ApiOperation(value="获取教练资料")
	public YiwuJson<CoachVO> get(@PathVariable(name="id") Integer id){
		
		try {
			EmployeeYzw emp = empService.get(id);
			return YiwuJson.createBySuccess(CoachVO.fromDAO(emp));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
}