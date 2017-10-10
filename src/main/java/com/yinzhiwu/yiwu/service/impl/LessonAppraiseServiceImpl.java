package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.LessonAppraiseDao;
import com.yinzhiwu.yiwu.entity.LessonAppraise;
import com.yinzhiwu.yiwu.service.LessonAppraiseService;

/**
*@Author ping
*@Time  创建时间:2017年10月10日下午5:43:12
*
*/

@Service
public class LessonAppraiseServiceImpl  extends BaseServiceImpl<LessonAppraise,Integer> implements LessonAppraiseService{

		@Autowired public void setBaseDao(LessonAppraiseDao laDao){super.setBaseDao(laDao);}
}
