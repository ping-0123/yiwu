package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.income.WithdrawEvent;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

public interface WithdrawEventService extends IBaseService<WithdrawEvent,Integer>{

	void saveWithdraw(int distributerId, int accountId, float amount) throws DataNotFoundException, Exception;

}
