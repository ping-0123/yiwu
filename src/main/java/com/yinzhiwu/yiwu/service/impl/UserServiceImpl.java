package com.yinzhiwu.yiwu.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.service.UserService;


@Service(value="userService")
public class UserServiceImpl extends BaseServiceImpl<Distributer, Integer> implements UserService {

	@Autowired 
	public void setBaseDao(DistributerDao distributerDao){
		super.setBaseDao(distributerDao);
	}

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
	public Distributer findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
