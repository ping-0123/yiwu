package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.EmployeePostYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
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
		if(eps.size()>1) throw new DataConsistencyException("一个员工不能有多个主职位");
		return eps.size()>0?eps.get(0):null;
	}

	
	private void cancelDefaultPost(EmployeeYzw emp) {
		try {
			EmployeePostYzw ep1 = findDefaultByEmployeeId(emp.getId());
			if(ep1!=null){
				ep1.setDefault(false);
				super.save(ep1);
			}
		} catch (DataConsistencyException e) {
			logger.error(e.getMessage(),e);
			List<EmployeePostYzw> eps = findAllDefaultByEmployeeId(emp.getId());
			for (EmployeePostYzw ep : eps) {
				ep.setDefault(false);
				super.save(ep);
			}
		}
	}
	

	/* (non-Javadoc)
	 * @see com.yinzhiwu.yiwu.dao.impl.BaseDaoImpl#save(java.lang.Object)
	 */
	@Override
	public Integer save(EmployeePostYzw ep) {
		if(ep.isDefault())
			cancelDefaultPost(ep.getEmployee());
		return super.save(ep);
	}

	/* (non-Javadoc)
	 * @see com.yinzhiwu.yiwu.dao.impl.BaseDaoImpl#update(java.lang.Object)
	 */
	@Override
	public void update(EmployeePostYzw ep) {
		if(ep.isDefault())
			cancelDefaultPost(ep.getEmployee());
		super.update(ep);
	}

	/* (non-Javadoc)
	 * @see com.yinzhiwu.yiwu.dao.impl.BaseDaoImpl#modify(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void modify(EmployeePostYzw source, EmployeePostYzw target) throws IllegalArgumentException, IllegalAccessException {
		if(!source.isDefault() && target.isDefault())
			cancelDefaultPost(source.getEmployee());
		super.modify(source, target);
	}
	
	
	
	private List<EmployeePostYzw> findAllDefaultByEmployeeId(Integer empId){
		return  findByProperties(
				new String[]{"employee.id", "isDefault"}, 
				new Object[]{empId, true});
	}

	@Override
	public DataTableBean<?> findDataTableByEmployeeId(QueryParameter parameter, Integer empId) throws NoSuchFieldException, SecurityException {
		return findDataTableByProperty(parameter, "employee.id", empId);
	}
}
