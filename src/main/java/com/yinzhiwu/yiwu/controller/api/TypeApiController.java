package com.yinzhiwu.yiwu.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.IncomeFactorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/type")
@Api(value = "type")
public class TypeApiController extends BaseController {

	@Autowired
	private IncomeFactorService incomeFactorService;

	@GetMapping("/getEventTypes")
	@ApiOperation(value = "获取能产生incomeTypeId类型收益的事件类型")
	public YiwuJson<List<EventType>> getEventTypes(
			@ApiParam(value = "收益类型Id； 10012:经验收益类型, 10013:基金收益类型, 10014：佣金收益类型 , -1:全部类型", required = true) int incomeTypeId) {
		List<EventType> types = incomeFactorService.getEventTypes(incomeTypeId);
		try {
			if (types == null || types.size() == 0)
				throw new Exception("没有找到相应的数据");
			return new YiwuJson<>(types);
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
}
