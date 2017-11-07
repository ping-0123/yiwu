package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.LessonTemplateDao;
import com.yinzhiwu.yiwu.entity.LessonTemplate;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;

/**
* @author 作者 ping
* @Date 创建时间：2017年10月30日 下午10:54:33
*
*/

@Repository
public class LessonTemplateDaoImpl extends BaseDaoImpl<LessonTemplate,Integer> implements LessonTemplateDao{

	@Override
	public DataTableBean<LessonTemplate> findDataTableByCourseTemplateId(QueryParameter parameter, Integer courseTemplateId) {
		try {
			return findDataTableByProperty(parameter, "courseTemplate.id", courseTemplateId);
		} catch (NoSuchFieldException | SecurityException e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Integer save(LessonTemplate template) {
		if(logger.isDebugEnabled()){
			String pictureUri = template.getConnotation()==null?null:template.getConnotation().getPictureUri();
			logger.debug("template.connotation.pictureUri is " + pictureUri);
		}
		return super.save(template);
	}
	
	
}
