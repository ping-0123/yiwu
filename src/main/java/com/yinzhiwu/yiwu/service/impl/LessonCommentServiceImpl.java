package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.LessonCommentDao;
import com.yinzhiwu.yiwu.entity.LessonComment;
import com.yinzhiwu.yiwu.service.LessonCommentService;

/**
*@Author ping
*@Time  创建时间:2017年10月10日下午5:43:12
*
*/

@Service
public class LessonCommentServiceImpl  extends BaseServiceImpl<LessonComment,Integer> implements LessonCommentService{

		@Autowired public void setBaseDao(LessonCommentDao laDao){super.setBaseDao(laDao);}
}
