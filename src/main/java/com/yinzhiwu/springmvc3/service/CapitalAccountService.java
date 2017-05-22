package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.CapitalAccount;
import com.yinzhiwu.springmvc3.model.CapitalAccountApiView;
import com.yinzhiwu.springmvc3.model.YiwuJson;

public interface CapitalAccountService extends IBaseService<CapitalAccount, Integer>{

	YiwuJson<CapitalAccountApiView> addCapitalAccount(CapitalAccountApiView v);

}
