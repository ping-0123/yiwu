package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.MediaDao;
import com.yinzhiwu.yiwu.entity.Media;
import com.yinzhiwu.yiwu.service.MediaService;

/**
*@Author ping
*@Time  创建时间:2017年10月7日下午3:35:20
*
*/

@Service
public class MediaServiceImpl extends BaseServiceImpl<Media,Integer> implements MediaService{

	@Autowired public void setBaseDao(MediaDao mediaDao){super.setBaseDao(mediaDao);}
}
