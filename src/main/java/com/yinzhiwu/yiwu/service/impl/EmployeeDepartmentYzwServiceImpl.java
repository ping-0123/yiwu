package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.EmployeeDepartmentYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeDepartmentYzw;
import com.yinzhiwu.yiwu.service.EmployeeDepartmentYzwService;

/**
*@Author ping
*@Time  创建时间:2017年7月28日下午5:54:00
*
*/

@Service
public class EmployeeDepartmentYzwServiceImpl extends BaseServiceImpl<EmployeeDepartmentYzw, Integer> implements EmployeeDepartmentYzwService{

	@Autowired
	public void setBaseDao(EmployeeDepartmentYzwDao employeeDepartmentYzwDao){
		super.setBaseDao(employeeDepartmentYzwDao);
	}
}
