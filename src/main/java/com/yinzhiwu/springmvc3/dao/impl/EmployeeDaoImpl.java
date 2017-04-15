package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.EmployeeDao;
import com.yinzhiwu.springmvc3.entity.Employee;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee,Integer> implements EmployeeDao{

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAllOnJobCoach() {
		String hql = "FROM Employee t1 join EmployeePost t2 on(t1.id=t2.employeeId)"
				+ " WHERE t1.removed=0 and t2.removed=0 and t2.postId=6"
				+ " ORDER BY convert_gbk(t1.name)";
		return (List<Employee>) getHibernateTemplate().find(hql);
	}

}
