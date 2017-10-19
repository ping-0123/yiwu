package com.yinzhiwu.yiwu.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yinzhiwu.yiwu.service.FileService;

/**
*@Author ping
*@Time  创建时间:2017年7月27日上午9:14:32
*
*/

@Service
public class FileServiceImpl implements FileService{
	
	@Value("${system.file.path}")
	private String path;
	@Value("${system.file.url}")
	private String url;
	@Value("${system.file.default_head_image_name}")
	private String defaultHeadImageName;
	
	/**
	 * 保存文件后返回保存的文件名， 如果需要返回Url方便直接访问使用{@link FileServiceImpl#getFileUrl(String)}
	 * @param file 需要保存的文件 如果文件不存在或者长度为0  抛出{@link IllegalArgumentException}
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@Override
	public String upload(MultipartFile file) throws IllegalStateException, IOException{
		if(file==null || file.getSize() ==0){
			throw new IllegalArgumentException("文件不存在");
		}
		String savedFileName= _generateFileName(file.getOriginalFilename());
		File f = new File(path + savedFileName);
		File folder = new File(path);
		if(! folder.exists()) folder.mkdirs();
		file.transferTo(f);
		return savedFileName;
	}
	
	
	@Override
	public String getFileUrl(String fileName) {
		if(null==fileName || "".equals(fileName.trim()))
			return "";
		return url + fileName;
	}
	
	public String getHeadImageUrl(String headImageName){
		if(null==headImageName || "".equals(headImageName.trim()))
			headImageName=  defaultHeadImageName;
		return url + headImageName;
	}
	private String _generateFileName(String originalFilename) {
		return System.currentTimeMillis() 
				+ originalFilename.substring(originalFilename.lastIndexOf(".")); 
	}

	@Override
	public String getImageUrl(String uri) {
		if(null==uri || uri.trim().length()==0)
			uri = defaultHeadImageName;
		return url + uri;
	}
}
