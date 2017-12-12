package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;

public interface CustomerYzwService extends IBaseService<CustomerYzw, Integer> {

	public CustomerYzw findByWechat(String wechat);

	public CustomerYzw findByMemberCard(String memberCard) throws DataNotFoundException;

	public CustomerYzw findByMobilePhone(String mobileNumber);

	public List<CustomerYzw> findAllByMobilePhone(String mobileNumber);
}
