package com.yinzhiwu.yiwu.service;

import java.util.Set;

import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;

public interface UserService extends IBaseService<EmployeeYzw, Integer>{

	Set<String> findRoles(String username);

	Set<String> findPermissions(String username);

	EmployeeYzw findByUsername(String username);
	
	void modifyPassword(Integer userId, String newPasswords);
}
