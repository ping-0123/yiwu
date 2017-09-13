package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.model.view.EmployeeApiView;

public interface EmployeeYzwService extends IBaseService<EmployeeYzw, Integer> {

	List<EmployeeApiView> getAllOnJobCoaches();

}
