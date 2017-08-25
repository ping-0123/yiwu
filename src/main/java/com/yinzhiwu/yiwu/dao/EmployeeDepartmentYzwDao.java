package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeDepartmentYzw;

/**
*@Author ping
*@Time  创建时间:2017年7月28日下午5:40:00
*
*/

public interface EmployeeDepartmentYzwDao extends IBaseDao<EmployeeDepartmentYzw, Integer> {


	List<DepartmentYzw> findDepartmentsByEmployee(int employeeId);

	List<Integer> findEmployeesUnderDepts(List<Integer> storeIds);

	DepartmentYzw findOneDepartmentByEmployee(Integer id);
	
}
