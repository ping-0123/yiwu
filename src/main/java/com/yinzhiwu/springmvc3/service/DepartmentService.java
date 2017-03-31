package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.Department;
import com.yinzhiwu.springmvc3.model.BriefDepartment;
import com.yinzhiwu.springmvc3.model.Store;

public interface DepartmentService {
	
	public List<BriefDepartment> findAllOperationDistricts();
	
	public List<BriefDepartment> findStoresByDistrictId(int districtId);
	
	public List<Store> findStoreInfosByDistrictId(int districtId);
	
	public Department findById(int id);
	
	public void show();
}
