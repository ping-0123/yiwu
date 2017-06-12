package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.income.DepositEvent;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

public interface DepositService extends IBaseService<DepositEvent,Integer> {

	void payDeposit(int distributerId, float amount, boolean fundsFirst) throws DataNotFoundException, Exception;

}
