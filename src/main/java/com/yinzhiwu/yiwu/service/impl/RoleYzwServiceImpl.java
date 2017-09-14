package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.RoleYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.RoleYzw;
import com.yinzhiwu.yiwu.service.RoleYzwService;

/**
*@Author ping
*@Time  创建时间:2017年9月14日下午7:53:56
*
*/

@Service
public class RoleYzwServiceImpl extends BaseServiceImpl<RoleYzw,Integer> implements RoleYzwService {

	@Autowired
	public void setBaseDao(RoleYzwDao roleYzwDao){
		super.setBaseDao(roleYzwDao);
	}
}
