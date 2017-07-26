package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface DistributerDao extends IBaseDao<Distributer, Integer> {

	public Distributer findByShareCode(String shareCode) throws DataNotFoundException;

	public Distributer findByPhoneNo(String phoneNo) throws DataNotFoundException;

	public Distributer findByMemberId(String memeberId) throws DataNotFoundException;

	public float getBeatRate(float exp);

	public Distributer findByWechat(String wechatNo) throws DataNotFoundException;

	public Distributer findByAccountPassword(String account, String password) throws Exception;

	/**
	 * 返回提成收入前三名的推广员
	 * 
	 * @return
	 */
	public List<Distributer> findTopThree();

	public int findCountByPhoneNo(String phoneNo);

	public int findCountByWechatNo(String wechatNo);

	public Distributer findByCustomerId(Integer customerId);
}
