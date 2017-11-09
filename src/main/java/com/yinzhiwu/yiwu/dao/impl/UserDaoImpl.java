package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.UserDao;
import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

	@Override
	public User findByUsername(String username) throws DataNotFoundException {
		return findOneByProperty("username", username);
	}


}
