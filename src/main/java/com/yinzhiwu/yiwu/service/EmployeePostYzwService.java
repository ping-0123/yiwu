package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;

public interface EmployeePostYzwService extends IBaseService<EmployeePostYzw,Integer> {

	List<EmployeePostYzw> findByEmployeeId(Integer empId);

	DataTableBean<?> findDataTableByEmployeeId(QueryParameter parameter, Integer empId) throws NoSuchFieldException, SecurityException;

}
