package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.EmployeeDepartmentYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeDepartmentYzw;

/**
*@Author ping
*@Time  创建时间:2017年7月28日下午5:40:58
*
*/

@Repository
public class EmployeeDepartmentYzwDaoImpl extends BaseDaoImpl<EmployeeDepartmentYzw,Integer> implements EmployeeDepartmentYzwDao {

	@Override
	public Integer save(EmployeeDepartmentYzw entity) {
		entity.init();
		return (Integer) getSession().save(entity);
	}

	@Override
	public void saveOrUpdate(EmployeeDepartmentYzw entity) {
		if(entity.getId() != null)
			entity.beforeUpdate();
		else
			entity.init();
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void update(EmployeeDepartmentYzw entity) {
		entity.beforeUpdate();
		getSession().update(entity);
	}

	@Override
	public List<DepartmentYzw> findDepartmentsByEmployee(int employeeId) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT d.department");
		hql.append(" FROM EmployeeDepartmentYzw d");
		hql.append(" WHERE d.employee.id=:employeeId");
		hql.append(" AND d.removed =:removed");
		List<DepartmentYzw> depts = getSession().createQuery(hql.toString(), DepartmentYzw.class)
				.setParameter("employeeId", employeeId)
				.setParameter("removed", false)
				.getResultList();
		if(depts == null ) return new ArrayList<>();
		return depts;
	}

	@Override
	public List<Integer> findEmployeesUnderDepts(List<Integer> storeIds) {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT DISTINCT t1.employee.id");
		hql.append(" FROM EmployeeDepartmentYzw t1");
		hql.append(" WHERE t1.removed=:removed");
		hql.append(" AND t1.department.id in :deptIds");
		List<Integer> list = getSession().createQuery(hql.toString(), Integer.class)
				.setParameter("removed", false)
				.setParameter("deptIds", storeIds)
				.getResultList();
		if(list == null)
			list = new ArrayList<>();
		return list;
	}
	
	

}
