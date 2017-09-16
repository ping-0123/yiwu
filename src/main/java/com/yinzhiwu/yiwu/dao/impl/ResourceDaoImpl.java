package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.ResourceDao;
import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.entity.sys.Resource.ResourceType;

/**
*@Author ping
*@Time  创建时间:2017年9月14日下午6:56:32
*
*/

@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource,Integer> implements ResourceDao{

	@Override
	public List<Resource> findRootMenus() {
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM Resource");
		hql.append(" WHERE parent.id is null");
		hql.append(" AND type=:type");
		
		return getSession().createQuery(hql.toString(), Resource.class)
				.setParameter("type", ResourceType.MENU)
				.getResultList();
		
	}

}
