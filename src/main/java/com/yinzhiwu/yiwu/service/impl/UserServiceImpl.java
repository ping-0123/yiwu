package com.yinzhiwu.yiwu.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.yinzhiwu.yiwu.context.Constants;
import com.yinzhiwu.yiwu.dao.UserDao;
import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.entity.sys.Role;
import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
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

	private Set<Role> findObjectRoles(User user){
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
		
		try {
			return findObjectRoles(findByUsername(username));
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return new HashSet<>();
		}
	}
	
	protected Set<String> findRoles(User user){
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
		try {
			return findRoles(findByUsername(username));
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return new HashSet<>();
		}
	}

	@Override
	public Set<String> findPermissions(String username) {
		Assert.hasText(username);
		try {
			return findPermissions(findByUsername(username));
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return new HashSet<>();
		}
	}


	protected Set<String> findPermissions(User user) {
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
	public User findByUsername(String username) throws DataNotFoundException {
		return userDao.findByUsername(username);
	}

	@Override
	public void modifyPassword(Integer userId, String newPasswords) {
	      User user;
		try {
			user = get(userId);
		} catch (DataNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
	      user.setPassword(newPasswords);
	      passwordHelper.encryptPassword(user);
	      update(user);
		
	}

	@Override
	public Integer save(User user) {
		if(!StringUtils.hasText(user.getPassword()))
			user.setPassword(Constants.PASSWORD_DEFAULT);
		passwordHelper.encryptPassword(user);
		return super.save(user);
	}

	
	
}
