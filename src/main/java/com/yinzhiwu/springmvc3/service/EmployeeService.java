package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.model.view.EmployeeApiView;

public interface EmployeeService {

	public List<EmployeeApiView> getAllOnJobCoaches();
}