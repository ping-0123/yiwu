package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.web.purchase.dto.CustomerDto;

public interface DistributerDao extends IBaseDao<Distributer, Integer> {

	public Distributer findByShareCode(String shareCode);

	public Distributer findByPhoneNo(String phoneNo) throws DataNotFoundException;

	public Distributer findByMemberCard(String memberCard) throws DataNotFoundException;

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

	public Distributer findByCustomerId(Integer customerId) throws DataNotFoundException;

	public PageBean<CustomerDto> findDtoPageByDistributerByKey(List<Integer> storeIds, List<Integer> employeeIds,
			List<Integer> distributerIds, String key, int pageNo, int pageSize);

	public List<Integer> findIdsByemployees(List<Integer> employeeIds);

	public CustomerYzw findCustomerByWechat(String wechatNO);
}
