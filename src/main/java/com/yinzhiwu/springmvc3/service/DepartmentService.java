package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.yzwOld.Department;
import com.yinzhiwu.springmvc3.model.Store;
import com.yinzhiwu.springmvc3.model.view.DepartmentApiView;

public interface DepartmentService {
	
	public List<DepartmentApiView> findAllOperationDistricts();
	
	public List<DepartmentApiView> findStoresByDistrictId(int districtId);
	
	public List<Store> findStoreInfosByDistrictId(int districtId);
	
	public Department findById(int id);
	
	public void show();

	public Store findStoreInfoById(int id);

	List<Store> findStoreByCities(String city);

	public List<Store> getAllStores();
}
