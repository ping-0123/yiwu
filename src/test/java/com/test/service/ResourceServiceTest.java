package com.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.entity.sys.Resource.ResourceType;
import com.yinzhiwu.yiwu.service.ResourceService;

/**
*@Author ping
*@Time  创建时间:2017年9月14日下午8:29:06
*
*/

public class ResourceServiceTest extends BaseSpringTest  {
	
	@Autowired private ResourceService resourceService;
	
//	@Test
	@Rollback(value=false)
	@Transactional
	public void initDatabase(){
		List<Resource> res = new ArrayList<>();
		Resource auth = new Resource("AUTHORITIES_MANAGER", "系统权限", null, "authorities:manager", ResourceType.MENU, null);
		res.add(auth);
		
		//组织架构
		Resource org_manager = new Resource("ORGANIZATIONS_MANAGER" ,"组织机构管理", "organizations","organizations:view:*", ResourceType.MENU, auth);
		res.add(org_manager);
		Resource org_create  = new Resource("ORGANIZATIONS_CREATE", "新增", null, "organizations:create:*", ResourceType.BUTTON, org_manager);
		res.add(org_create);
		Resource org_update  = new Resource("ORGANIZATIONS_UPDATE", "修改", null, "organizations:update:*", ResourceType.BUTTON, org_manager);
		res.add(org_update);
		Resource org_delete  = new Resource("ORGANIZATIONS_DELETE", "删除", null, "organizations:delete:*", ResourceType.BUTTON, org_manager);
		res.add(org_delete);
		
		//用户管理
		Resource user_manager = new Resource("USERS_MANAGER" ,"用户管理", "users","users:view:*", ResourceType.MENU, auth);
		res.add(user_manager);
		res.add(new Resource("USERS_CREATE" ,"新增", null,"users:create:*", ResourceType.BUTTON, user_manager));
		res.add(new Resource("USERS_UPDATE" ,"修改", null,"users:update:*", ResourceType.BUTTON, user_manager));
		res.add(new Resource("USERS_DELETE" ,"删除", null,"users:delete:*", ResourceType.BUTTON, user_manager));
		
		//角色管理
		Resource role_manager = new Resource("ROLES_MANAGER" ,"角色管理", "roles","roles:view:*", ResourceType.MENU, auth);
		res.add(role_manager);
		res.add(new Resource("ROLES_CREATE" ,"新增", null,"roles:create:*", ResourceType.BUTTON, role_manager));
		res.add(new Resource("ROLES_UPDATE" ,"修改", null,"roles:update:*", ResourceType.BUTTON, role_manager));
		res.add(new Resource("ROLES_DELETE" ,"删除", null,"roles:delete:*", ResourceType.BUTTON, role_manager));
		
		//资源
		Resource resources_manager = new Resource("RESOURCES_MANAGER" ,"资源管理", "resources","resources:view:*", ResourceType.MENU, auth);
		res.add(resources_manager);
		res.add(new Resource("RESOURCES_CREATE" ,"新增", null,"resources:create:*", ResourceType.BUTTON, resources_manager));
		res.add(new Resource("RESOURCES_UPDATE" ,"修改", null,"resources:update:*", ResourceType.BUTTON, resources_manager));
		res.add(new Resource("RESOURCES_DELETE" ,"删除", null,"resources:delete:*", ResourceType.BUTTON, resources_manager));
		
		
		//持久化
		for (Resource resource : res) {
			resourceService.save(resource);
		}
	}

//	@Test
	@Rollback(value=false)
	public void addHRManager(){
		
		List<Resource> res = new ArrayList<>();
		Resource hr = new Resource("HR_MANAGER", "人事行政", null, "HR:manager", ResourceType.MENU, null);
		res.add(hr);
		
		Resource employees = new Resource("EMPLOYEES_MANAGER" ,"员工信息", "employees","employees:view:*", ResourceType.MENU, hr);
		res.add(employees);
		res.add(new Resource("EMPLOYEES_CREATE" ,"新增", null,"employees:create:*", ResourceType.BUTTON, employees));
		res.add(new Resource("EMPLOYEES_UPDATE" ,"修改", null,"employees:update:*", ResourceType.BUTTON, employees));
		res.add(new Resource("EMPLOYEES_DELETE" ,"删除", null,"employees:delete:*", ResourceType.BUTTON, employees));
		
		//组织架构
		Resource dept = new Resource("DEPARTMENTS_MANAGER" ,"组织架构", "departments","departments:view:*", ResourceType.MENU, hr);
		res.add(dept);
		res.add(new Resource("DEPARTMENTS_CREATE" ,"新增", null,"departments:create:*", ResourceType.BUTTON, dept));
		res.add(new Resource("DEPARTMENTS_UPDATE" ,"修改", null,"departments:update:*", ResourceType.BUTTON, dept));
		res.add(new Resource("DEPARTMENTS_DELETE" ,"删除", null,"departments:delete:*", ResourceType.BUTTON, dept));
		
		Resource posts = new Resource("POSTS_MANAGER" ,"职位", "posts","posts:view:*", ResourceType.MENU, hr);
		res.add(posts);
		res.add(new Resource("POSTS_CREATE" ,"新增", null,"posts:create:*", ResourceType.BUTTON, posts));
		res.add(new Resource("POSTS_UPDATE" ,"修改", null,"posts:update:*", ResourceType.BUTTON, posts));
		res.add(new Resource("POSTS_DELETE" ,"删除", null,"posts:delete:*", ResourceType.BUTTON, posts));

		
		//持久化
		for (Resource resource : res) {
			resourceService.save(resource);
		}
	}
	
	@Test
	public void init(){
		System.out.println("fuck the world!");
	}
}
