package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface UserDao extends IBaseDao<User, Integer>{

	User findByUsername(String username) throws DataNotFoundException;
}
