package com.yinzhiwu.yiwu.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.impl.BaseDaoImpl;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;

/**
*@Author ping
*@Time  创建时间:2017年7月26日下午5:21:39
*
*/

@Repository
public class EmployeeYzwDaoImpl extends BaseDaoImpl<EmployeeYzw,Integer> implements EmployeeYzwDao {

	@Override
	public EmployeeYzw findByPhoneNo(String phoneNo) throws Exception {
			List<EmployeeYzw> emps =findByProperties(
					new String[]{"cellPhone", "removed"}, 
					new Object[]{phoneNo, false});
			if(emps == null || emps.size() == 0 )
				return null;
			if(emps.size()> 1){
				logger.error(phoneNo + "在 employee表中重复");
				throw new Exception(phoneNo + "在 employee表中重复");
			}
			return emps.get(0);
	}

	@Override
	public EmployeeYzw findByTel(String phoneNo) {
		List<EmployeeYzw> emps =findByProperties(
				new String[]{"telephone", "removed"}, 
				new Object[]{phoneNo, false});
		if(emps==null || emps.size() ==0)
			return null;
		return emps.get(0);
	}

	@Override
	public EmployeeYzw findByUsername(String username) {
		List<EmployeeYzw> emps =findByProperty("username", username);

		if(emps==null || emps.size() == 0)
			return null;
		return emps.get(0);
	}

	@Override
	@Where(clause="removed=false")
	public DataTableBean<EmployeeYzw> findDataTable(QueryParameter parameter) {
		return super.findDataTableByProperty(parameter, "removed", false);
	}

	@Override
	@Where(clause="removed=false")
	public DataTableBean<EmployeeYzw> findDataTableByProperties(QueryParameter parameter, String[] properties, Object[] values) {
		return super.findDataTableByProperties(parameter, properties, values);
	}

	@Override
	@Where(clause="removed=false")
	public DataTableBean<EmployeeYzw> findDataTableByProperty(QueryParameter parameter, String propertyName, Object value) {
		return super.findDataTableByProperty(parameter, propertyName, value);
	}

	@Override
	public List<EmployeeYzw> findByNullNumber() {
		
		StringBuilder hql = new StringBuilder();
		hql.append("FROM EmployeeYzw");
		hql.append(" WHERE number is null or number = ''"); 
		return getSession().createQuery(hql.toString(), EmployeeYzw.class)
				.getResultList();
	}


	@Override
	public List<EmployeeYzw> findAll(Specification<EmployeeYzw> spec){
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<EmployeeYzw> criteria = builder.createQuery(EmployeeYzw.class);
		Root<EmployeeYzw> root = criteria.from(EmployeeYzw.class);
		criteria.where(spec.toPredicate(root, criteria, builder));
		
		return getSession().createQuery(criteria)
				.getResultList();
	}
	
}
