package com.yinzhiwu.yiwu.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.YiwuJson.ReturnCode;

/**
*@Author ping
*@Time  创建时间:2017年8月14日下午5:28:13
*
*/
@RestController
@RequestMapping(value="/api/error")
public class ErrorApiController {

	@GetMapping(value="/unlogin")
	public YiwuJson<String> error(){
		return YiwuJson.createByErrorCodeMessage(ReturnCode.NEED_LOGIN.getCode(), ReturnCode.NEED_LOGIN.getDesc());
	}
}
