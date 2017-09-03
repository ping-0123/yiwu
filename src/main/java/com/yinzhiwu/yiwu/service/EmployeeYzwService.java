package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;

public interface EmployeeYzwService extends IBaseService<EmployeeYzw, Integer> {

	EmployeeYzw findByUsername(String username);
	void changePassword(Integer id, String newPassword);

}
