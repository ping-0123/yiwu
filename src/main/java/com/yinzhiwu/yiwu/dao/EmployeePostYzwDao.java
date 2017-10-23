package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.exception.DataConsistencyException;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;

/**
*@Author ping
*@Time  创建时间:2017年7月29日上午11:08:12
*
*/

public interface EmployeePostYzwDao extends IBaseDao<EmployeePostYzw, Integer> {

	List<EmployeePostYzw> findByEmployeeId(Integer empId);

	EmployeePostYzw findDefaultByEmployeeId(Integer id) throws DataConsistencyException;

	DataTableBean<?> findDataTableByEmployeeId(QueryParameter parameter, Integer empId) throws NoSuchFieldException, SecurityException;

	DataTableBean<EmployeePostYzw> findDataTableByPostId(QueryParameter parameter, Integer postId);


}
