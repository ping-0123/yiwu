package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;

public interface DepartmentYzwDao extends IBaseDao<DepartmentYzw, Integer> {

	List<DepartmentYzw> findAllStores();

	List<DepartmentYzw> findAllStoresUnderOrganization(int companyId);

	List<DepartmentYzw> findAllStoresUnderOrganizations(List<DepartmentYzw> depts);

	List<Integer> findStoreIdsByEmplyee(int employeeId);

}
