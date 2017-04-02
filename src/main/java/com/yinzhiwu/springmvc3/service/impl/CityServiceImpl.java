package com.yinzhiwu.springmvc3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	@Qualifier("departmentDaoImpl")
	private DepartmentDao departmentDao;
	
	@Override
	public List<String> getCities() {
		return departmentDao.findCities();
	}

}
