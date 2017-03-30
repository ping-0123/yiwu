package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.entity.Department;
import com.yinzhiwu.springmvc3.model.BriefDepartment;
import com.yinzhiwu.springmvc3.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	@Qualifier("departmentDaoImplTwo")
	private DepartmentDao departmentDao;

	@Override
	public List<BriefDepartment> findAllOperationDistricts() {
		List<Department> list = departmentDao.findAllOperationDistricts();
		List<BriefDepartment> l = new ArrayList<>();
		for (Department d : list) {
			l.add(new BriefDepartment(d));
		}
		
		return l;
	}

	@Override
	public List<BriefDepartment> findStoresByDistrictId(int districtId) {
		List<Department> list = departmentDao.findStoresByDistrictId(districtId);
		List<BriefDepartment> l = new ArrayList<>();
		for (Department d : list) {
			l.add(new BriefDepartment(d));
		}
		return l;
	}

	@Override
	public Department findById(int id) {
		return departmentDao.findById(id);
	}

	@Override
	public void show() {
		departmentDao.show();
	}

}
