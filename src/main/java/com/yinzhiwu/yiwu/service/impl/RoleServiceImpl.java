package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.RoleDao;
import com.yinzhiwu.yiwu.entity.sys.Role;

/**
*@Author ping
*@Time  创建时间:2017年8月29日下午5:17:14
*
*/

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,Integer>{

	@Autowired public void setBaseDao(RoleDao roleDao){
		super.setBaseDao(roleDao);
	}
}
