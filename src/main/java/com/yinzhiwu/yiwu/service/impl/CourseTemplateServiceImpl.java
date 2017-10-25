package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CourseTemplateDao;
import com.yinzhiwu.yiwu.entity.CourseTemplate;
import com.yinzhiwu.yiwu.service.CourseTemplateService;

/**
*@Author ping
*@Time  创建时间:2017年10月25日下午1:49:15
*
*/

@Service
public class CourseTemplateServiceImpl extends BaseServiceImpl<CourseTemplate,Integer> implements CourseTemplateService {
	
	@Autowired public void setBaseDao(CourseTemplateDao dao){super.setBaseDao(dao);}
}
