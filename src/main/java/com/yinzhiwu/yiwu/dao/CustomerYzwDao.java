package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface CustomerYzwDao extends IBaseDao<CustomerYzw, Integer> {
	public CustomerYzw findByWeChat(String weChatNo);

	public CustomerYzw findByPhoneNo(String phoneNo) throws DataNotFoundException;

	public CustomerYzw findByPhoneByWechat(String phoneNo, String wechatNo);

	public CustomerYzw getByMemberCard(String memberCard) throws DataNotFoundException;

	public CustomerYzw findByMemberCard(String memberCard) throws DataNotFoundException;

}
