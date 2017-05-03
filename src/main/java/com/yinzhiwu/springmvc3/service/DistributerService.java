package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.model.DistributerApiView;
import com.yinzhiwu.springmvc3.model.YiwuJson;

public interface DistributerService extends IBaseService<Distributer, Integer> {

	YiwuJson<DistributerApiView> register(String invitationCode, Distributer distributer);

	YiwuJson<DistributerApiView> loginByWechat(String wechatNo);

	YiwuJson<DistributerApiView> loginByAccount(String account, String password);

}
