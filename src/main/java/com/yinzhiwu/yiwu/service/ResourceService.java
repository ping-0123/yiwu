package com.yinzhiwu.yiwu.service;

import java.util.List;
import java.util.Set;

import com.yinzhiwu.yiwu.entity.sys.Resource;

/**
*@Author ping
*@Time  创建时间:2017年9月14日下午6:53:33
*
*/

public interface ResourceService extends IBaseService<Resource,Integer> {

	List<Resource> findMenus(Set<String> permissions);

}
