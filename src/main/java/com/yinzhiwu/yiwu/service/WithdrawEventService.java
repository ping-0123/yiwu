package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.income.WithdrawEvent;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

public interface WithdrawEventService extends IBaseService<WithdrawEvent,Integer>{

	void saveWithdraw(int distributerId, int accountId, float amount) throws DataNotFoundException, Exception;

}
