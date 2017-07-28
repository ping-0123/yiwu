package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.EmployeeDepartmentYzwDao;
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
	
	

}
