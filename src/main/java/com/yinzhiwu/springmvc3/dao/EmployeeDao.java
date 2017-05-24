package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.Employee;
import com.yinzhiwu.springmvc3.model.view.EmployeeApiView;

public interface EmployeeDao extends IBaseDao<Employee, Integer>{
	
	public List<EmployeeApiView> findAllOnJobCoach();
}
