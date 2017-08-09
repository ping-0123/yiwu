package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.DepartmentDao;
import com.yinzhiwu.yiwu.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	@Qualifier("departmentDaoImplTwo")
	private DepartmentDao departmentDao;

	@Override
	public List<String> getCities() {
		return departmentDao.findCities();
	}

}
