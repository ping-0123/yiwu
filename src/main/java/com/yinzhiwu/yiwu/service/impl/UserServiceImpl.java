package com.yinzhiwu.yiwu.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.EmployeeYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.entity.yzw.RoleYzw;
import com.yinzhiwu.yiwu.service.PasswordHelper;
import com.yinzhiwu.yiwu.service.RoleYzwService;
import com.yinzhiwu.yiwu.service.UserService;


@Service(value="userService")
public class UserServiceImpl extends BaseServiceImpl<EmployeeYzw, Integer> implements UserService {

	@Autowired 
	public void setBaseDao(EmployeeYzwDao employeeYzwDao){
		super.setBaseDao(employeeYzwDao);
	}
	
	@Autowired private EmployeeYzwDao employeeDao;
	@Autowired private RoleYzwService roleService;
	@Autowired private PasswordHelper passwordHelper;

	public Set<RoleYzw> findObjectRoles(String username){
		Assert.hasText(username);
		
		Set<RoleYzw> roles = new LinkedHashSet<>();
		if("Admin".equals(username)){
			 roles.addAll(roleService.findAll());
			 return roles;
		}
		EmployeeYzw user = employeeDao.findByUsername(username);
		if(user != null)
			roles.addAll(user.getRoles());
		return roles;
	}
	
	@Override
	public Set<String> findRoles(String username) {
		Assert.hasText(username);
		
		Set<String> roles = new LinkedHashSet<>();
		for (RoleYzw role : findObjectRoles(username)) {
			roles.add(role.getName());
		}
		
		return roles;
	}

	@Override
	public Set<String> findPermissions(String username) {
		Assert.hasText(username);
		
		Set<String> permissions = new LinkedHashSet<>();
		if("Admin".equals(username) ){
			permissions.add("*:*:*");
			return permissions;
		}
		
		EmployeeYzw user = employeeDao.findByUsername(username);
		if(user == null)
			return permissions;
		else
			return user.getStringPermissions();
	}


	@Override
	public Set<String> findPermissions(EmployeeYzw emp) {
		Assert.notNull(emp);
		
		Set<String> permissions = new LinkedHashSet<>();
		if("Admin".equals(emp.getUsername()) ){
			permissions.add("*:*:*");
			return permissions;
		}
		
		return emp.getStringPermissions();
	}
	
	@Override
	public EmployeeYzw findByUsername(String username) {
		return employeeDao.findByUsername(username);
	}

	@Override
	public void modifyPassword(Integer userId, String newPasswords) {
	      EmployeeYzw user =employeeDao.get(userId);
	      user.setPassword(newPasswords);
	      passwordHelper.encryptPassword(user);
	      employeeDao.update(user);
		
	}

	
	
}
