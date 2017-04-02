package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.entity.Department;

@Repository
public class DepartmentDaoImpl 
	extends BaseDaoImpl<Department, Integer> 
	implements DepartmentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findAllOperationDistricts() {
		String hql = "SELECT new Department(id,deptName) FROM Department d "
				+ "WHERE d.deptName LIKE '%区域' AND d.superiorId = 55 AND d.removed=0";
		return (List<Department>) getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findStoresByDistrictId(int districtId) {
		String hql = "SELECT new Department(id,deptName) FROM Department WHERE superiorId = :districtId AND removed = 0";
		return (List<Department>) getHibernateTemplate().find(hql, districtId);
	}

	@Override
	public Department findById(int id) {
		return get(id);
	}

	@Override
	public void show() {
		System.out.println("DepartmentDaoImpl show method");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findCities(){
		String hql ="select distinct city from Department where city <> '' and city is not null";
		return (List<String>) getHibernateTemplate().find(hql);
	}
}
