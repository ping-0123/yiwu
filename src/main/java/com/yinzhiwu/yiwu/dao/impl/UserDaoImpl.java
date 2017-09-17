package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.UserDao;
import com.yinzhiwu.yiwu.entity.sys.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

	@Override
	public User findByUsername(String username) {
		return findOneByProperty("username", username);
	}


}
