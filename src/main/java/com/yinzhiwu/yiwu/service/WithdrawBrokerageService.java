package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.WithdrawBrokerage;

/**
 * 
 * @author ping
 * @Date 2017年10月29日 下午9:56:33
 *
 */
public interface WithdrawBrokerageService  extends IBaseService<WithdrawBrokerage,Integer>{

	WithdrawBrokerage doWithdraw(WithdrawBrokerage withdraw);

	WithdrawBrokerage doPayWithdraw(WithdrawBrokerage withdraw) ;


}
