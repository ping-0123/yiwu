package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.hibernate.annotations.Where;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.EmployeePostYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.exception.DataConsistencyException;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;

/**
*@Author ping
*@Time  创建时间:2017年7月29日上午11:08:59
*
*/

@Repository
public class EmployeePostYzwDaoImpl extends BaseDaoImpl<EmployeePostYzw, Integer> implements EmployeePostYzwDao {

	@Override
	public List<EmployeePostYzw> findByEmployeeId(Integer empId) {
		return findByProperty("employee.id", empId);
	}

	@Override
	public EmployeePostYzw findDefaultByEmployeeId(Integer empId) throws DataConsistencyException {
		List<EmployeePostYzw> eps = findByProperties(
				new String[]{"employee.id", "isDefault"}, 
				new Object[]{empId, true});
		if(eps.size()>1) {
			logger.error("eps.size is " +  eps.size());
			throw new DataConsistencyException("一个员工不能有多个主职位");
		}
		return eps.size()>0?eps.get(0):null;
	}

	
	private void cancelDefaultPostExcludeSelf(EmployeePostYzw ep) {
//		try {
//			EmployeePostYzw ep1 = findDefaultByEmployeeId(emp.getId());
//			if(ep1!=null){
//				ep1.setDefault(false);
//				super.save(ep1);
//			}
//		} catch (DataConsistencyException e) {
//			logger.error(e.getMessage(),e);
//			List<EmployeePostYzw> eps = findAllDefaultByEmployeeId(emp.getId());
//			for (EmployeePostYzw ep : eps) {
//				ep.setDefault(false);
//				super.save(ep);
//			}
//		}
		StringBuilder hql = new StringBuilder();
		hql.append("UPDATE EmployeePostYzw");
		hql.append(" SET isDefault = :false");
		hql.append(" WHERE isDefault = :true");
		hql.append(" AND employee.id=:empId");
		hql.append(" AND id <> :id");
		
		getSession().createQuery(hql.toString())
			.setParameter("false", false)
			.setParameter("true", true)
			.setParameter("empId", ep.getEmployee().getId())
			.setParameter("id", ep.getId())
			.executeUpdate();
	}
	

	/* (non-Javadoc)
	 * @see com.yinzhiwu.yiwu.dao.impl.BaseDaoImpl#save(java.lang.Object)
	 */
	@Override
	public Integer save(EmployeePostYzw ep) {
		if(ep.isDefault())
			cancelDefaultPostExcludeSelf(ep);
		return super.save(ep);
	}

	/* (non-Javadoc)
	 * @see com.yinzhiwu.yiwu.dao.impl.BaseDaoImpl#update(java.lang.Object)
	 */
	@Override
	public void update(EmployeePostYzw ep) {
		if(ep.isDefault())
			cancelDefaultPostExcludeSelf(ep);
		super.update(ep);
	}

	
	@SuppressWarnings("unused")
	private List<EmployeePostYzw> findAllDefaultByEmployeeId(Integer empId){
		return  findByProperties(
				new String[]{"employee.id", "isDefault"}, 
				new Object[]{empId, true});
	}

	@Override
	@Where(clause="removed=false")
	public DataTableBean<?> findDataTableByEmployeeId(QueryParameter parameter, Integer empId) throws NoSuchFieldException, SecurityException {
		return findDataTableByProperty(parameter, "employee.id", empId);
	}

	@Override
	@Where(clause="removed=false")
	public DataTableBean<EmployeePostYzw> findDataTableByPostId(QueryParameter parameter, Integer postId) {
		try {
			return findDataTableByProperty(parameter, "post.id", postId);
		} catch (NoSuchFieldException | SecurityException e) {
			return null;
		}
	}
}
