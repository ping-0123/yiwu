package com.yinzhiwu.yiwu.controller.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.model.view.FileApiView;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.service.impl.FileServiceImpl;

import io.swagger.annotations.Api;

/**
*@Author ping
*@Time  创建时间:2017年7月26日下午7:06:48
*
*/

@RestController
@RequestMapping(value="/api/file")
@Api(value="/file", description="文件图片上传服务")
public class FileApiController extends BaseController {
	
	@Autowired private  FileService fileService;
	
	@PostMapping(value="")
	public FileApiView upload(MultipartFile file){
		FileApiView  view = new FileApiView();
		try {
			String url = fileService.upload(file);
			view.setUrl(url);
		} catch (IllegalStateException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		return view;
	}

}
