package com.yinzhiwu.yiwu.event;

import org.springframework.context.ApplicationEvent;

import com.yinzhiwu.yiwu.entity.WithdrawBrokerage;

/**
* @author 作者 ping
* @Date 创建时间：2017年10月30日 上午12:07:01
*
*/

@SuppressWarnings("serial")
public class PayWithdrawEvent extends ApplicationEvent {

	public PayWithdrawEvent(WithdrawBrokerage withdraw) {
		super(withdraw);
	}

}
