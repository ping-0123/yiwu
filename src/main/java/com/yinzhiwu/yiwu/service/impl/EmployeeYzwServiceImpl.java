package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.EmployeeYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;

@Service
public class EmployeeYzwServiceImpl extends BaseServiceImpl<EmployeeYzw, Integer> implements EmployeeYzwService {

	@Autowired public void setBaseDao(EmployeeYzwDao empDao){
		super.setBaseDao(empDao);
	}
	
	@Autowired private EmployeeYzwDao employeeDao;
	
	@Override
	public EmployeeYzw findByUsername(String username) {
		Assert.hasText(username);
		
		return employeeDao.findByUsername(username);
	}

	@Override
	public void changePassword(Integer id, String newPassword) {
		// TODO Auto-generated method stub
		
	}

}
