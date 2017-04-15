package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.Employee;

public interface EmployeeDao extends IBaseDao<Employee, Integer>{
	
	public List<Employee> findAllOnJobCoach();
}
