package com.yinzhiwu.springmvc3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.EmployeeDao;
import com.yinzhiwu.springmvc3.model.EmployeeApiView;
import com.yinzhiwu.springmvc3.service.EmployeeService;



@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

	
	@Override
	public List<EmployeeApiView> getAllOnJobCoaches() {
		return employeeDao.findAllOnJobCoach();
	}

}
