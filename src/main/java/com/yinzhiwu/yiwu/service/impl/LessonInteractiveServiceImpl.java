package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.LessonInteractiveDao;
import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.service.LessonInteractiveService;

/**
*@Author ping
*@Time  创建时间:2017年10月27日上午10:04:00
*
*/

@Service
public class LessonInteractiveServiceImpl extends BaseServiceImpl<LessonInteractive,Integer> implements LessonInteractiveService {
	
	@Autowired public void setBaseDao(LessonInteractiveDao dao){super.setBaseDao(dao);}
}
