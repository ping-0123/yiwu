package com.yinzhiwu.yiwu.service;

import java.util.Set;

import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;

public interface EmployeeYzwService extends IBaseService<EmployeeYzw, Integer> {

	public Set<DepartmentYzw> getVisableDepartments(EmployeeYzw employee);
}
