package com.yinzhiwu.springmvc3.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.entity.Department;

@Repository
public class DepartmentDaoImplTwo extends BaseDaoImpl<Department, Integer>
	implements DepartmentDao {


    
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findAllOperationDistricts() {
		String hql = "SELECT new Department(id,deptName)"
				+ " FROM Department d "
				+ "WHERE d.deptName LIKE '%区域' AND d.superiorId = 55 AND d.removed=0"
				+ " order by convert_gbk(d.deptName)";
		return (List<Department>) getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findStoresByDistrictId(int districtId) {
		String hql = "SELECT new Department(id,deptName) "
				+ "FROM Department "
				+ "WHERE superiorId = :districtId AND removed = 0"
				+ "ORDER by convert_gbk(deptName)";
		return (List<Department>) getHibernateTemplate()
				.findByNamedParam(hql, "districtId", districtId);
	}

	@Override
	public Department findById(int id) {
		return getHibernateTemplate().get(Department.class, id);
	}

	@Override
	public void show() {
		System.out.println("DepartmentDaoImplTwo show method");		
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<String> findCities(){
		String hql ="select distinct city from Department where city <> '' and city is not null";
		return (List<String>) getHibernateTemplate().find(hql);
	}

	@Override
	public List<Department> findAllStores() {
		List<Department> districts = findAllOperationDistricts();
		List<Department> stores =new ArrayList<>();
		for (Department district : districts) {
			List<Department> s= findStoresByDistrictId(district.getId());
			stores.addAll(s);
		}
		return stores;
	}
	

}
