package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw.OrgnizationType;
import com.yinzhiwu.yiwu.model.view.DepartmentApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;

public interface DepartmentYzwDao extends IBaseDao<DepartmentYzw, Integer> {

	List<DepartmentYzw> findAllStores();

	List<DepartmentYzw> findAllStoresUnderOrganization(int companyId);
	List<DepartmentYzw> findAllStoresUnderOrganizations(List<DepartmentYzw> depts);
	List<StoreApiView> findStoreApiViewsUnderOrganization(Integer deptId);

	List<Integer> findStoreIdsByEmplyee(int employeeId);

	List<DepartmentApiView> findAllOperationDistricts();

	List<DepartmentYzw> findByCity(String city);

	List<DepartmentYzw> findByType(OrgnizationType store);

	/**
	 * @return
	 */
	public List<String> findCities();

	/**
	 * @param city
	 * @return
	 */
	public List<String> findDistrictsByCity(String city);

	/**
	 * @param disctrict
	 * @return
	 */
	Object findStoresByAdministrativeDistrict(String disctrict);


}
