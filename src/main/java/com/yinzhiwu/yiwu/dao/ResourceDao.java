package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.sys.Resource;

/**
*@Author ping
*@Time  创建时间:2017年9月14日下午6:55:58
*
*/

public interface ResourceDao extends IBaseDao<Resource,Integer>{

	List<Resource> findRootMenus();

}
