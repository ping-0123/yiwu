package com.yinzhiwu.yiwu.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.service.QiniuService;

/**
*@Author ping
*@Time  创建时间:2017年10月16日下午9:13:31
*
*/

@RestController
@RequestMapping("/system/qiniu")
public class QiniuController extends BaseController{
	
	@Autowired private QiniuService qiniuService;
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/uploadToken")
	public Map getUploadToken(){
		String uptoken =  qiniuService.createToken();
		Map<String, String> map = new HashMap<>();
		map.put("uptoken",uptoken);
		return map;
	}
}
