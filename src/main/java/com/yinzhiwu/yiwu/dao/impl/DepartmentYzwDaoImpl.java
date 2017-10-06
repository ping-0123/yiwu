package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.DepartmentYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.model.view.DepartmentApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;

@Repository
public class DepartmentYzwDaoImpl extends BaseDaoImpl<DepartmentYzw, Integer> implements DepartmentYzwDao {

	@Override
	public void delete(DepartmentYzw dept) {
		if(dept.getChildren().size()>0)
			throw new UnsupportedOperationException(dept.getName()+ "存在子部门， 清先将子部门删除");
		super.delete(dept);
	}
	
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
		hql.append(" AND d.name like :storeSuffix");
//		hql.append(" AND d.operationDistrict <> '加盟中心'");
		hql.append(" AND d.path like :supriorDeptId");
		hql.append(" ORDER BY convert_gbk(d.name)");
		
		List<DepartmentYzw> depts = getSession().createQuery(hql.toString(), DepartmentYzw.class)
				.setParameter("removed", false)
				.setParameter("storeSuffix", "%店")
				.setParameter("supriorDeptId", "%" + companyId + "%")
				.getResultList();
		if(depts == null) return new ArrayList<>();
		return depts;
	}
	
	@Override
	public List<StoreApiView> findStoreApiViewsUnderOrganization(Integer deptId) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new com.yinzhiwu.yiwu.model.view.StoreApiView(d.id, d.name)");
		hql.append(" FROM DepartmentYzw d");
		hql.append(" WHERE d.removed = :removed");
		hql.append(" AND d.name like :storeSuffix");
//		hql.append(" AND d.operationDistrict <> '加盟中心'");
		hql.append(" AND d.path like :supriorDeptId");
		hql.append(" ORDER BY convert_gbk(d.name)");
		
		List<StoreApiView> depts = getSession().createQuery(hql.toString(), StoreApiView.class)
				.setParameter("removed", false)
				.setParameter("storeSuffix", "%店")
				.setParameter("supriorDeptId", "%" + deptId + "%")
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

	@Override
	public List<Integer> findStoreIdsByEmplyee(int employeeId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT d.id");
		hql.append(" FROM DepartmentYzw d");
		hql.append(" WHERE (d.manager1.id = :manager1");
		hql.append(" OR d.manager2.id = :manager2)");
		hql.append(" AND d.removed = :removed");
		List<Integer> list = getSession().createQuery(hql.toString(), Integer.class)
				.setParameter("manager1", employeeId)
				.setParameter("manager2", employeeId)
				.setParameter("removed", false)
				.getResultList();
		if(list == null)
			list = new ArrayList<>();
		return list;
	}

	@Override
	public List<DepartmentApiView> findAllOperationDistricts() {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new com.yinzhiwu.yiwu.model.view.DepartmentApiView");
		hql.append("(d.id, d.name)");
		hql.append(" FROM DepartmentYzw d");
		hql.append(" WHERE d.name LIKE :district");
		hql.append(" AND d.parent.id = :superiorId");
		hql.append(" AND d.removed = :removed");
		hql.append(" ORDER BY convert_gbk(d.name)");
		List<DepartmentApiView> views = getSession().createQuery(hql.toString(), DepartmentApiView.class)
				.setParameter("district", "%区域")
				.setParameter("superiorId", 55)
				.setParameter("removed", false)
				.getResultList();
		if(views == null)
			return new ArrayList<>();
		return views;
	}

	@Override
	public List<DepartmentYzw> findByCity(String city) {
		Assert.hasText(city);
		
		return findByProperty("officialAddress.city", city);
	}

	
}
