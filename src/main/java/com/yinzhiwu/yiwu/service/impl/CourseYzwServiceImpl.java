package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.CourseYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.Connotation;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.service.CourseYzwService;
import com.yinzhiwu.yiwu.service.FileService;

@Service
public class CourseYzwServiceImpl extends BaseServiceImpl<CourseYzw, String> implements CourseYzwService {
	@SuppressWarnings("unused")
	@Autowired private CourseYzwDao courseDao;
	@Qualifier("fileServiceImpl")
	@Autowired private FileService fileService;

	@Autowired
	public void setBaseDao(CourseYzwDao courseYzwDao) {
		super.setBaseDao(courseYzwDao);
	}
	
	@Override
	public  void setConnatationUrls(Connotation con){
		Assert.notNull(con);
		con.setAudioUri(fileService.getFileUrl(con.getAudioUri()));
		con.setPictureUri(fileService.getFileUrl(con.getPictureUri()));
		con.setVideoUri(fileService.getFileUrl(con.getVideoUri()));
	}

}
