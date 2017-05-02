package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.model.YiwuJson;

public interface DistributerService extends IBaseService<Distributer, Integer> {

	YiwuJson<Distributer> register(String invitationCode, Distributer distributer);

}
