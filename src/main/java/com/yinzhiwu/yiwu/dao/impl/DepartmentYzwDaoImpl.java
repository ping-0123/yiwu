package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.DepartmentYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.web.purchase.dto.StoreDto;

@Repository
public class DepartmentYzwDaoImpl extends BaseDaoImpl<DepartmentYzw, Integer> implements DepartmentYzwDao {

	@Override
	public List<DepartmentYzw> findAllStores() {
		StringBuilder hql = new StringBuilder();
//		hql.append("SELECT new com.yinzhiwu.yiwu.web.purchase.dto.StoreDto(d.id, d.name)");
		hql.append(" FROM DepartmentYzw d");
		hql.append(" WHERE d.removed = :removed");
		hql.append(" AND d.name like '%店'");
		hql.append(" AND d.operationDistrict <> '加盟中心'");
		hql.append(" ORDER BY convert_gbk(d.name)");
		List<DepartmentYzw> depts = getSession().createQuery(hql.toString(), DepartmentYzw.class)
				.setParameter("removed", false)
				.getResultList();
		if(depts == null) return new ArrayList<>();
		return depts;
	}

	@Override
	public List<DepartmentYzw> findAllStoresUnderOrganization(int companyId) {
		StringBuilder hql = new StringBuilder();
//		hql.append("SELECT new com.yinzhiwu.yiwu.web.purchase.dto.StoreDto(d.id, d.name)");
		hql.append(" FROM DepartmentYzw d");
		hql.append(" WHERE d.removed = :removed");
		hql.append(" AND d.name like '%店'");
//		hql.append(" AND d.operationDistrict <> '加盟中心'");
		hql.append(" AND d.path like '%" + companyId + "%'");
		hql.append(" ORDER BY convert_gbk(d.name)");
		
		List<DepartmentYzw> depts = getSession().createQuery(hql.toString(), DepartmentYzw.class)
				.setParameter("removed", false)
				.getResultList();
		if(depts == null) return new ArrayList<>();
		return depts;
	}

	@Override
	public List<DepartmentYzw> findAllStoresUnderOrganizations(List<DepartmentYzw> depts) {
		StringBuilder hql = new StringBuilder();
//		hql.append("SELECT new com.yinzhiwu.yiwu.web.purchase.dto.StoreDto(d.id, d.name)");
		hql.append(" FROM DepartmentYzw d");
		hql.append(" WHERE d.removed = :removed");
		hql.append(" AND d.name like '%店'");
//		hql.append(" AND d.operationDistrict <> '加盟中心'");
		hql.append(" AND (0=1");
		for (DepartmentYzw dept : depts) {
			if(dept.getId() != null)
				hql.append(" OR d.path like '%" + dept.getId().toString() + "%'");
		}
		hql.append(")");
		
		hql.append(" ORDER BY convert_gbk(d.name)");
		List<DepartmentYzw> stores = getSession().createQuery(hql.toString(), DepartmentYzw.class)
				.setParameter("removed", false)
				.getResultList();
		if(stores == null) return new ArrayList<>();
		return stores;
	}

}
