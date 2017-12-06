package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.EmployeeDepartmentYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeDepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;

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
		hql.append("SELECT t1.department");
		hql.append(" FROM EmployeeDepartmentYzw t1");
		hql.append(" WHERE t1.employee.id=:employeeId");
		hql.append(" AND t1.removed =:removed");
		List<DepartmentYzw> depts = getSession().createQuery(hql.toString(), DepartmentYzw.class)
				.setParameter("employeeId", employeeId)
				.setParameter("removed", false)
				.getResultList();
		if(depts == null ) return new ArrayList<>();
		return depts;
	}

	@Override
	public List<Integer> findEmployeesUnderDepts(List<Integer> storeIds) {
		List<Integer> list  = null;
		
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT DISTINCT t1.employee.id");
		hql.append(" FROM EmployeeDepartmentYzw t1");
		hql.append(" WHERE t1.removed=:removed");
		if(storeIds != null && storeIds.size() >0){
			hql.append(" AND t1.department.id in :deptIds");
			list = getSession().createQuery(hql.toString(), Integer.class)
					.setParameter("removed", false)
					.setParameter("deptIds", storeIds)
					.getResultList();
		}else {
			list = getSession().createQuery(hql.toString(), Integer.class)
					.setParameter("removed", false)
					.getResultList();
		}
		
		if(list == null) list = new ArrayList<>();
		return list;
	}

	@Override
	public DepartmentYzw findOneDepartmentByEmployee(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT t1.department");
		hql.append(" FROM EmployeeDepartmentYzw t1");
		hql.append(" WHERE t1.employee.id=:employeeId");
		hql.append(" AND t1.removed =:removed");
		
		try{
			return getSession().createQuery(hql.toString(), DepartmentYzw.class)
					.setParameter("employeeId", id)
					.setParameter("removed", false)
					.setMaxResults(1)
					.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public EmployeeYzw findStoreManager(Integer storeId) {
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<EmployeeDepartmentYzw> criteria = builder.createQuery(EmployeeDepartmentYzw.class);
		Root<EmployeeDepartmentYzw> root = criteria.from(EmployeeDepartmentYzw.class);
//		criteria.select(root.join())
		
		return null;
	}

	@Override
	public EmployeeYzw findOneSalesman(Integer storeId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
