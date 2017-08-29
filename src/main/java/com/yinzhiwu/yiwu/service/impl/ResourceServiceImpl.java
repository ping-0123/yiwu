package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.ResourceDao;
import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.service.ResourceService;

/**
*@Author ping
*@Time  创建时间:2017年8月29日下午5:08:55
*
*/

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource,Integer> implements ResourceService {

	@Autowired public void setBaseDao(ResourceDao resourceDao){
		super.setBaseDao(resourceDao);
	}
	
}
