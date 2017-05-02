package com.yinzhiwu.springmvc3.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.EmployeeDao;
import com.yinzhiwu.springmvc3.entity.Employee;
import com.yinzhiwu.springmvc3.model.EmployeeApiView;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee,Integer> implements EmployeeDao{

	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeApiView> findAllOnJobCoach() {
		String hql = "SELECT new Employee(t1.id,t1.name) FROM Employee t1 join EmployeePost t2 on(t1.id=t2.employeeId)"
				+ " WHERE t1.removed=0 and t2.removed=0 and t2.postId=6"
				+ " ORDER BY convert_gbk(t1.name)";
		List<Employee> emps =  (List<Employee>) getHibernateTemplate().find(hql);
		List<EmployeeApiView> empAVs = new ArrayList<>();
		for (Employee emp : emps) {
			empAVs.add(new EmployeeApiView(emp));
		}
		
		return empAVs;
	}

}
