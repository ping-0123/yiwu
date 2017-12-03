package com.yinzhiwu.yiwu.service;

import java.util.Set;

import com.yinzhiwu.yiwu.entity.sys.Role;
import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface UserService extends IBaseService<User, Integer>{

	Set<String> findRoles(String username);
	Set<Role> findObjectRoles(String username);
	
	Set<String> findPermissions(String username);

	User findByUsername(String username) throws DataNotFoundException;
	
	void modifyPassword(Integer userId, String newPasswords);
	EmployeeYzw findEmployeeByUserId(Integer id);


}
