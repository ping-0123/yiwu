package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.LessonTemplate;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;

/**
* @author 作者 ping
* @Date 创建时间：2017年10月30日 下午10:55:21
*
*/

public interface LessonTemplateService extends IBaseService<LessonTemplate,Integer> {

	DataTableBean<LessonTemplate> findDataTableByCourseTemplateId(QueryParameter parameter, Integer id);

}
