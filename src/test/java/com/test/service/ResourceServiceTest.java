package com.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.test.BaseTest;
import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.entity.sys.Resource.ResourceType;
import com.yinzhiwu.yiwu.service.ResourceService;

/**
*@Author ping
*@Time  创建时间:2017年9月14日下午8:29:06
*
*/

public class ResourceServiceTest extends BaseTest  {
	
	@Autowired private ResourceService resourceService;
	
	@Test
	@Rollback(value=false)
	@Transactional
	public void initDatabase(){
		List<Resource> res = new ArrayList<>();
		Resource auth = new Resource("AUTHORITIES_MANAGER", "系统权限", null, "authority:manager", ResourceType.MENU, null);
		res.add(auth);
		
		//组织架构
		Resource org_manager = new Resource("ORGANIZATIONS_MANAGER" ,"组织机构管理", "/organizations","organizations:view:*", ResourceType.MENU, auth);
		res.add(org_manager);
		Resource org_create  = new Resource("ORGANIZATIONS_CREATE", "新增", null, "organizations:create:*", ResourceType.BUTTON, org_manager);
		res.add(org_create);
		Resource org_update  = new Resource("ORGANIZATIONS_UPDATE", "修改", null, "organizations:update:*", ResourceType.BUTTON, org_manager);
		res.add(org_update);
		Resource org_delete  = new Resource("ORGANIZATIONS_DELETE", "删除", null, "organizations:delete:*", ResourceType.BUTTON, org_manager);
		res.add(org_delete);
		
		//用户管理
		Resource user_manager = new Resource("USERS_MANAGER" ,"用户管理", "/users","users:view:*", ResourceType.MENU, auth);
		res.add(user_manager);
		res.add(new Resource("USERS_CREATE" ,"新增", null,"users:create:*", ResourceType.BUTTON, user_manager));
		res.add(new Resource("USERS_UPDATE" ,"修改", null,"users:update:*", ResourceType.BUTTON, user_manager));
		res.add(new Resource("USERS_DELETE" ,"删除", null,"users:delete:*", ResourceType.BUTTON, user_manager));
		
		//角色管理
		Resource role_manager = new Resource("ROLES_MANAGER" ,"角色管理", "/roles","roles:view:*", ResourceType.MENU, auth);
		res.add(role_manager);
		res.add(new Resource("ROLES_CREATE" ,"新增", null,"roles:create:*", ResourceType.BUTTON, role_manager));
		res.add(new Resource("ROLES_UPDATE" ,"修改", null,"roles:update:*", ResourceType.BUTTON, role_manager));
		res.add(new Resource("ROLES_DELETE" ,"删除", null,"roles:delete:*", ResourceType.BUTTON, role_manager));
		
		//资源
		Resource resources_manager = new Resource("RESOURCES_MANAGER" ,"资源管理", "/resources","resources:view:*", ResourceType.MENU, auth);
		res.add(resources_manager);
		res.add(new Resource("RESOURCES_CREATE" ,"新增", null,"resources:create:*", ResourceType.BUTTON, resources_manager));
		res.add(new Resource("RESOURCES_UPDATE" ,"修改", null,"resources:update:*", ResourceType.BUTTON, resources_manager));
		res.add(new Resource("RESOURCES_DELETE" ,"删除", null,"resources:delete:*", ResourceType.BUTTON, resources_manager));
		
		
		//持久化
		for (Resource resource : res) {
			resourceService.save(resource);
		}
	}
}
