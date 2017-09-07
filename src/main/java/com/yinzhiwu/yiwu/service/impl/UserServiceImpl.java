package com.yinzhiwu.yiwu.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.EmployeeYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.service.PasswordHelper;
import com.yinzhiwu.yiwu.service.UserService;


@Service(value="userService")
public class UserServiceImpl extends BaseServiceImpl<EmployeeYzw, Integer> implements UserService {

	@Autowired 
	public void setBaseDao(EmployeeYzwDao employeeYzwDao){
		super.setBaseDao(employeeYzwDao);
	}
	
	@Autowired
	private EmployeeYzwDao employeeDao;
	@Autowired private PasswordHelper passwordHelper;

	@Override
	public Set<String> findRoles(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> findPermissions(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeYzw findByUsername(String username) {
		return employeeDao.findByUsername(username);
	}

	@Override
	public void moidifyPasswords(Integer userId, String newPasswords) {
	      EmployeeYzw user =employeeDao.get(userId);
	      user.setPassword(newPasswords);
	      passwordHelper.encryptPassword(user);
	      employeeDao.update(user);
		
	}
	
	
}
