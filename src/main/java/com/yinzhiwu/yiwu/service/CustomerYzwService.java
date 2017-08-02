package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;

public interface CustomerYzwService extends IBaseService<CustomerYzw, Integer> {

	public CustomerYzw findByWechat(String wechat);
}
