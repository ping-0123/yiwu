package com.yinzhiwu.yiwu.service;

import java.util.Set;

import com.yinzhiwu.yiwu.entity.sys.Role;
import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface UserService extends IBaseService<User, Integer>{

	Set<String> findRoles(String username);
	Set<Role> findObjectRoles(String username);
	Set<String> findRoles(User user);
	Set<Role> findObjectRoles(User user);
	
	Set<String> findPermissions(String username);
	Set<String> findPermissions(User user);

	User findByUsername(String username) throws DataNotFoundException;
	
	void modifyPassword(Integer userId, String newPasswords);


}
