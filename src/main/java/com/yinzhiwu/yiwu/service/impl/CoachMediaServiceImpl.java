package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.MediaDao;
import com.yinzhiwu.yiwu.entity.CoachMedia;
import com.yinzhiwu.yiwu.service.CoachMediaService;

/**
*@Author ping
*@Time  创建时间:2017年10月7日下午3:35:20
*
*/

@Service
public class CoachMediaServiceImpl extends BaseServiceImpl<CoachMedia,Integer> implements CoachMediaService{

	@Autowired public void setBaseDao(MediaDao mediaDao){super.setBaseDao(mediaDao);}
}
