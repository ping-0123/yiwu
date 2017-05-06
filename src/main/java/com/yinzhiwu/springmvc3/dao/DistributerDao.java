package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import org.hibernate.exception.DataNotFoundException;

import com.yinzhiwu.springmvc3.entity.Distributer;

public interface DistributerDao extends IBaseDao<Distributer, Integer> {
	
	public Distributer findByShareCode(String shareCode) throws DataNotFoundException;
	
	public Distributer findByPhoneNo(String phoneNo) throws DataNotFoundException;
	
	public Distributer findByMemberId(String memeberId) throws DataNotFoundException;

	public float getBeatRate(float exp);

	public Distributer findByWechat(String wechatNo) throws DataNotFoundException;

	public Distributer findByAccountPassword(String account, String password) throws  Exception;

	Integer saveBean(Distributer entity) throws Exception;

	/**
	 * 返回提成收入前三名的推广员
	 * @return
	 */
	public List<Distributer> findTopThree();
}
