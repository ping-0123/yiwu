package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.income.DepositEvent;

public interface DepositService extends IBaseService<DepositEvent,Integer> {


	void saveDeposit(int distributerId, float amount, boolean fundsFirst) throws Exception;

}
