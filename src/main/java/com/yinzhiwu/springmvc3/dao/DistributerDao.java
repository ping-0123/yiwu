package com.yinzhiwu.springmvc3.dao;

import org.hibernate.exception.DataNotFoundException;

import com.yinzhiwu.springmvc3.entity.Distributer;

public interface DistributerDao extends IBaseDao<Distributer, Integer> {
	
	public Distributer findByShareCode(String shareCode) throws DataNotFoundException;
	
	public Distributer findByPhoneNo(String phoneNo) throws DataNotFoundException;
	
	public Distributer findByMemberId(String memeberId) throws DataNotFoundException;

}
