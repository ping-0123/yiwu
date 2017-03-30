package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.entity.Department;

@SuppressWarnings("deprecation")
@Repository
public class DepartmentDaoImpl 
	extends BaseDaoImpl<Department, Integer> 
	implements DepartmentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findAllOperationDistricts() {
		String hql = "SELECT new Department(id,deptName) FROM Department d "
				+ "WHERE d.deptName LIKE '%区域' AND d.superiorId = 55 AND d.removed=0";
		Query query = getSession().createQuery(hql);
		return (List<Department>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findStoresByDistrictId(int districtId) {
		String hql = "SELECT new Department(id,deptName) FROM Department WHERE superiorId = :districtId AND removed = 0";
//		return (List<Department>) getHibernateTemplate().find(hql);
		Query query = getSession().createQuery(hql);
		query.setInteger("districtId", districtId);
		return (List<Department>) query.list();
	}

	@Override
	public Department findById(int id) {
		return get(id);
	}

	@Override
	public void show() {
		System.out.println("DepartmentDaoImpl show method");
	}


}
