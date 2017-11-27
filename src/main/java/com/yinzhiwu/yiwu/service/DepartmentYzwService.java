package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.model.view.DepartmentApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;
import com.yinzhiwu.yiwu.web.purchase.dto.StoreDto;

/**
*@Author ping
*@Time  创建时间:2017年7月28日上午9:07:48
*
*/

public interface DepartmentYzwService extends IBaseService<DepartmentYzw, Integer>{

	List<StoreDto> findAllStores(int employeeId);
	List<DepartmentYzw> findAllStores();

	List<StoreDto> findVisableStoresByEmployee(int employeeId);

	List<DepartmentApiView> findAllOperationDistricts();
	List<StoreApiView> findStoreApiViewsUnderOrganization(Integer districtId);

	void move(DepartmentYzw source, DepartmentYzw target);

	List<DepartmentYzw> findAllWithExclude(DepartmentYzw source);

	List<DepartmentYzw> findByCity(String city);

	public String translateCommaSeparateIdsToNames(String  commaSeparateIds);
}
