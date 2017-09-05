package com.yinzhiwu.yiwu.service;

import java.util.Set;

import com.yinzhiwu.yiwu.entity.Distributer;

public interface UserService extends IBaseService<Distributer, Integer>{

	Set<String> findRoles(String username);

	Set<String> findPermissions(String username);

	Distributer findByUsername(String username);
}
