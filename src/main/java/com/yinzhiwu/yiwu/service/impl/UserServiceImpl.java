package com.yinzhiwu.yiwu.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.UserDao;
import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.entity.sys.Role;
import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.service.PasswordHelper;
import com.yinzhiwu.yiwu.service.RoleService;
import com.yinzhiwu.yiwu.service.UserService;


@Service(value="userService")
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

	@Autowired 
	public void setBaseDao(UserDao dao){
		super.setBaseDao(dao);
	}
	
	@Autowired private UserDao userDao;
	@Autowired private RoleService roleService;
	@Autowired private PasswordHelper passwordHelper;

	@Override
	public Set<Role> findObjectRoles(User user){
		Assert.notNull(user);
		Set<Role> roles = new HashSet<>();
		if("Admin".equals(user.getUsername())){
			 roles.addAll(roleService.findAll());
			 return roles;
		}
		
		roles.addAll(user.getRoles());
		EmployeeYzw emp = user.getEmployee();
		if(emp != null){
			for (EmployeePostYzw empPost : emp.getEmployeePosts()) {
				//TODO 检测里面是否包含已删除的用户岗位信息
				if(empPost.getPost() != null)
					roles.addAll(empPost.getPost().getRoles());
			}
		}
		return roles;
	}
	
	@Override
	public Set<Role> findObjectRoles(String username){
		Assert.hasText(username);
		
		return findObjectRoles(findByUsername(username));
	}
	
	@Override
	public Set<String> findRoles(User user){
		Assert.notNull(user);
		
		Set<String> roles = new HashSet<>();
		for (Role role : findObjectRoles(user)) {
			roles.add(role.getName());
		}
		
		return roles;
	}
	
	@Override
	public Set<String> findRoles(String username) {
		Assert.hasText(username);
		return findRoles(findByUsername(username));
	}

	@Override
	public Set<String> findPermissions(String username) {
		Assert.hasText(username);
		return findPermissions(findByUsername(username));
	}


	@Override
	public Set<String> findPermissions(User user) {
		Assert.notNull(user);
		
		Set<String> permissions = new HashSet<>();
		if("Admin".equals(user.getUsername())){
			permissions.add("*:*:*");
			return permissions;
		}
		
		Set<Role> roles = findObjectRoles(user);
		for (Role role : roles) {
			for (Resource res : role.getResources()) {
				permissions.add(res.getPermission());
			}
		}
		
		return permissions;
	}
	
	@Override
	@Cacheable(value="service", key="#username" /**keyGenerator="keyGenerator"*/)
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public void modifyPassword(Integer userId, String newPasswords) {
	      User user =get(userId);
	      user.setPassword(newPasswords);
	      passwordHelper.encryptPassword(user);
	      update(user);
		
	}

	
	
}
