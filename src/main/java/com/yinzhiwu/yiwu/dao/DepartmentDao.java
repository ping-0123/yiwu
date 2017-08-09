package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzwOld.Department;

public interface DepartmentDao extends IBaseDao<Department, Integer> {

	public List<Department> findAllOperationDistricts();

	public List<Department> findStoresByDistrictId(int districtId);

	public Department findById(int id);

	public void show();

	List<String> findCities();

	public List<Department> findAllStores();

}
