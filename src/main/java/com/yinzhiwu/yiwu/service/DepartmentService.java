package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzwOld.Department;
import com.yinzhiwu.yiwu.model.view.DepartmentApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;

public interface DepartmentService {

	public List<DepartmentApiView> findAllOperationDistricts();

	public List<DepartmentApiView> findStoresByDistrictId(int districtId);

	public List<StoreApiView> findStoreInfosByDistrictId(int districtId);

	public Department findById(int id);

	public void show();

	public StoreApiView findStoreInfoById(int id);

	List<StoreApiView> findStoreByCities(String city);

	public List<StoreApiView> getAllStores();
}
