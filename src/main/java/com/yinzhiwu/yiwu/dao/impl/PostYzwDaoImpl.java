package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.PostYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.PostYzw;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;

/**
*@Author ping
*@Time  创建时间:2017年7月29日上午11:18:41
*
*/

@Repository
public class PostYzwDaoImpl  extends BaseDaoImpl<PostYzw,Integer> implements PostYzwDao{

	@Override
	public DataTableBean findDataTable(Integer start, Integer length) {
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM PostYzw");
		List<PostYzw> posts =  getSession().createQuery(hql.toString(),PostYzw.class) 
				.setFirstResult(start)
				.setMaxResults(length)
				.getResultList();
		
		Long count = findCount();
		
		return new DataTableBean(1,count.intValue(),count.intValue(),posts, "");
	}

}
