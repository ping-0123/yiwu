package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.RoleDao;
import com.yinzhiwu.yiwu.entity.sys.Role;
import com.yinzhiwu.yiwu.service.RoleService;

@Service
public class PostYzwServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService {

	@Autowired
	public void setBaseDao(RoleDao dao){super.setBaseDao(dao);}
}
