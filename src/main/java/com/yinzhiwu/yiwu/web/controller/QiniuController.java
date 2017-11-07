package com.yinzhiwu.yiwu.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.service.FileService;

/**
*@Author ping
*@Time  创建时间:2017年10月16日下午9:13:31
*
*/

@RestController
@RequestMapping("/system/qiniu")
public class QiniuController extends BaseController{
	
	@Qualifier("qiniuServiceImpl")
	@Autowired private FileService qiniuService;
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/uploadToken")
	public Map getUploadToken(){
		String uptoken =  qiniuService.createAccessToken();
		Map<String, String> map = new HashMap<>();
		map.put("uploadToken",uptoken);
		return map;
	}
	
	/**
	 * special format needed by qiniu-js-sdk
	 * @return
	 */
	@GetMapping("/uptoken")
	public Map<String, String> getUptoken(){
		Map<String, String> map = new HashMap<>();
		map.put("uptoken", qiniuService.createAccessToken());
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping(value="/{key}")
	public Map deleteFile(@PathVariable(name="key") String key){
		return (Map) new HashMap<>()
				.put("result", qiniuService.delete(key));
	}
	
}
