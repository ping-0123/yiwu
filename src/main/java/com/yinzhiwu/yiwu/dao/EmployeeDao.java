package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzwOld.Employee;
import com.yinzhiwu.yiwu.model.view.EmployeeApiView;

public interface EmployeeDao extends IBaseDao<Employee, Integer>{
	
	public List<EmployeeApiView> findAllOnJobCoach();
}
