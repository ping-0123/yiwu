package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.yzw.PostYzw;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;

/**
*@Author ping
*@Time  创建时间:2017年7月29日上午11:17:23
*
*/

public interface PostYzwDao extends IBaseDao<PostYzw,Integer> {

	DataTableBean findDataTable(Integer start, Integer length);

}
