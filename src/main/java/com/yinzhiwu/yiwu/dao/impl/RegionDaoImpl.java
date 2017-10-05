package com.yinzhiwu.yiwu.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.RegionDao;
import com.yinzhiwu.yiwu.entity.Region;

@Repository
public class RegionDaoImpl  implements RegionDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Region get(Integer id){
		return getSession().get(Region.class, id);
	}
}
