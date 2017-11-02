package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.LessonTemplateDao;
import com.yinzhiwu.yiwu.entity.LessonTemplate;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.service.LessonTemplateService;

/**
* @author 作者 ping
* @Date 创建时间：2017年10月30日 下午10:55:52
*
*/

@Service
public class LessonTemplateServiceImpl extends BaseServiceImpl<LessonTemplate,Integer> implements LessonTemplateService {
	
	@Autowired private LessonTemplateDao lessonTemplateDao;
	
	@Autowired public void setBaseDao(LessonTemplateDao dao){super.setBaseDao(dao);}

	@Override
	public DataTableBean<LessonTemplate> findDataTableByCourseTemplateId(QueryParameter parameter, Integer id) {
		return lessonTemplateDao.findDataTableByCourseTemplateId(parameter,id);
	}
}
