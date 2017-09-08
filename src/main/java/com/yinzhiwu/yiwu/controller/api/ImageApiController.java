package com.yinzhiwu.yiwu.controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.impl.FileService;

/**
*@Author ping
*@Time  创建时间:2017年9月8日上午11:22:05
*
*/

@RestController
@RequestMapping(value="/api/images")
public class ImageApiController extends BaseController {
		
	@Autowired private FileService fileService;
	
	@PostMapping
	public YiwuJson<?> upload(MultipartFile image){
		if(image !=null && image.getSize() > 0){
			try {
				String fileName = fileService.upload(image);
				Map<String, String> map = new HashMap<>();
				map.put("imageName", fileName);
				map.put("imageUrl", fileService.getFileUrl(fileName));
				return YiwuJson.createBySuccess("上传成功", map);
			} catch (IllegalStateException | IOException e) {
				logger.error(e.getMessage(),e);
				return YiwuJson.createByErrorMessage(e.getMessage());
			}
		}
		
		return YiwuJson.createByIllegalArgumentError();
	}

}
