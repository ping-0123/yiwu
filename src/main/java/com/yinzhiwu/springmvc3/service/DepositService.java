package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.income.DepositEvent;

public interface DepositService extends IBaseService<DepositEvent,Integer> {


	void saveDeposit(int distributerId, float amount, boolean fundsFirst) throws Exception;

}
