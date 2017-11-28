package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface CustomerYzwDao extends IBaseDao<CustomerYzw, Integer> {
	public CustomerYzw findByWeChat(String weChatNo);

	public CustomerYzw findByPhoneNo(String phoneNo);

	public CustomerYzw findByPhoneByWechat(String phoneNo, String wechatNo);

	public CustomerYzw getByMemberCard(String memberCard) throws DataNotFoundException;

	public CustomerYzw findByMemberCard(String memberCard) throws DataNotFoundException;

	public List<CustomerYzw> findAllByMobilePhone(String mobileNumber);

}
