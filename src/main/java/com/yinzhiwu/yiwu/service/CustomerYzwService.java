package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface CustomerYzwService extends IBaseService<CustomerYzw, Integer> {

	public CustomerYzw findByWechat(String wechat);

	public CustomerYzw findByMemberCard(String memberCard) throws DataNotFoundException;
}
