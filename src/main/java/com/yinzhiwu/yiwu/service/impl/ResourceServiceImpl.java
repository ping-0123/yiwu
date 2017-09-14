package com.yinzhiwu.yiwu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yinzhiwu.yiwu.dao.ResourceDao;
import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.entity.sys.Resource.ResourceType;
import com.yinzhiwu.yiwu.service.ResourceService;

/**
*@Author ping
*@Time  创建时间:2017年9月14日下午6:54:26
*
*/

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource,Integer> implements ResourceService{

		@Autowired
		public void setBaseDao(ResourceDao resourceDao){
			super.setBaseDao(resourceDao);
		}

		
		@Override
		public List<Resource> findMenus(Set<String> permissions) {
			List<Resource> resources = findAll();
			List<Resource> menus = new ArrayList<>();
			for (Resource resource : resources) {
				if(ResourceType.MENU == resource.getType() 
						&& hasPermission(permissions, resource))
					menus.add(resource);
			}
			return menus;
		}
		
		private boolean hasPermission(Set<String> permissions, Resource resource ){
			if(StringUtils.isEmpty(resource.getPermission()))
				return true;
			for (String perm : permissions) {
				WildcardPermission p1 = new WildcardPermission(perm);
				WildcardPermission p2 = new WildcardPermission(resource.getPermission());
				if(p1.implies(p2))
					return true;
			}
			
			return false;
		}
}
