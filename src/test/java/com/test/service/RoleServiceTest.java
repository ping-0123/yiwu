package com.test.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.test.BaseTest;
import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.entity.yzw.RoleYzw;
import com.yinzhiwu.yiwu.service.ResourceService;
import com.yinzhiwu.yiwu.service.RoleYzwService;

/**
*@Author ping
*@Time  创建时间:2017年9月15日下午8:25:43
*
*/

public class RoleServiceTest extends BaseTest {

	@Autowired private RoleYzwService roleService;
	@Autowired private ResourceService resourceService;
	
	@Test
	@Rollback(value=false)
	public void addSomeResource(){
		RoleYzw role = roleService.get(4);
		Resource r1 = resourceService.get(40032);
		Resource r2 = resourceService.get(40033);
		Set<Resource> res = new LinkedHashSet<>();
		res.add(r1);
		res.add(r2);
		role.setResources(res);
		roleService.update(role);
	}
}
