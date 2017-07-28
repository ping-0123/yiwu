package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.DepartmentYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;

/**
*@Author ping
*@Time  创建时间:2017年7月28日上午9:08:33
*
*/

@Service
public class DepartmentYzwServiceImpl extends BaseServiceImpl<DepartmentYzw, Integer> implements DepartmentYzwService{

	@Autowired
	public void setBaseDao(DepartmentYzwDao departmentYzwDao){
		super.setBaseDao(departmentYzwDao);
	}
}
