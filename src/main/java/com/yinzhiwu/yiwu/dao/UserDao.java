package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.sys.User;

public interface UserDao extends IBaseDao<User, Integer>{

	User findByUsername(String username);
}
