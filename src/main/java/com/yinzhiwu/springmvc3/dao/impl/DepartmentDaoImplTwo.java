package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.entity.Department;

@Repository
public class DepartmentDaoImplTwo extends HibernateDaoSupport
	implements DepartmentDao {

    @Autowired  
    public void setHibernateSessionFactory(SessionFactory sessionFactory) {  
        super.setSessionFactory(sessionFactory);
    }  
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findAllOperationDistricts() {
		String hql = "SELECT new Department(id,deptName)"
				+ " FROM Department d "
				+ "WHERE d.deptName LIKE '%区域' AND d.superiorId = 55 AND d.removed=0";
		return (List<Department>) getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findStoresByDistrictId(int districtId) {
		String hql = "SELECT new Department(id,deptName) "
				+ "FROM Department "
				+ "WHERE superiorId = :districtId AND removed = 0"
				+ "ORDER by deptName";
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
	
	

}
