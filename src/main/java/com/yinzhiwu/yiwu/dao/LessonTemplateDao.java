package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.LessonTemplate;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;

/**
* @author 作者 ping
* @Date 创建时间：2017年10月30日 下午10:54:04
*
*/

public interface LessonTemplateDao extends IBaseDao<LessonTemplate	,Integer> {

	DataTableBean<LessonTemplate> findDataTableByCourseTemplateId(QueryParameter parameter, Integer id);

}
