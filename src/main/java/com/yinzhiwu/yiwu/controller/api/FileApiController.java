package com.yinzhiwu.yiwu.controller.api;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.model.view.FileApiView;

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
	
	@Value("${system.file.path}")
	private String path;
	@Value("${system.file.url}")
	private String url;
	
	@PostMapping(value="")
	public FileApiView upload(MultipartFile file){
		FileApiView  view = new FileApiView();
		if(file==null || file.getSize() ==0)
			return new FileApiView(300, null, null);
		String fileName= _generateFileName(file.getOriginalFilename());
		File f = new File(path + fileName);
		File folder = new File(path);
		if(!folder.exists()) folder.mkdirs();
		try {
			file.transferTo(f);
			view.setName(fileName);
			view.setUrl(_generateFileUrl(fileName));
		} catch (IllegalStateException e) {
			view.setReturnCode(400);
			e.printStackTrace();
		}catch(IOException e){
			view.setReturnCode(500);
		}
		
		return view;
	}

	private String _generateFileUrl(String fileName) {
		return url + fileName;
	}

	private String _generateFileName(String originalFilename) {
		return System.currentTimeMillis() + "_" + originalFilename; 
	}
}
