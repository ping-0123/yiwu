package com.yinzhiwu.yiwu.service;

import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.PayDeposit;
import com.yinzhiwu.yiwu.exception.business.BusinessException;

/**
*@Author ping
*@Time  创建时间:2017年11月10日上午9:53:36
*
*/

public interface PayDepositService extends IBaseService<PayDeposit,Integer>{

	@Transactional
	void payDeposit(Distributer distributer, float amount, boolean fundsFirst) throws BusinessException;

}
