package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.yzw.PostYzw;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;

/**
*@Author ping
*@Time  创建时间:2017年9月14日下午7:50:49
*
*/

public interface PostYzwService extends IBaseService<PostYzw,Integer> {

	DataTableBean findDataTable(Integer start, Integer length);
	
}
