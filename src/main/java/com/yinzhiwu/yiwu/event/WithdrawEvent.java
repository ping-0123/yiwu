package com.yinzhiwu.yiwu.event;

import org.springframework.context.ApplicationEvent;

import com.yinzhiwu.yiwu.entity.WithdrawBrokerage;

/**
*@Author ping
*@Time  创建时间:2017年11月9日下午3:22:19
*
*/

@SuppressWarnings("serial")
public class WithdrawEvent extends ApplicationEvent implements IncomeEvent{

	public WithdrawEvent(WithdrawBrokerage withdraw) {
		super(withdraw);
	}

	@Override
	public IncomeEventType getType() {
		return IncomeEventType.WITHDRAW;
	}

	@Override
	public String getSourceId() {
		return ((WithdrawBrokerage) getSource()).getId().toString();
	}

	@Override
	public Object getSubject() {
		return  ((WithdrawBrokerage) getSource()).getDistributer();
	}

	@Override
	public Float getValue() {
		return ((WithdrawBrokerage) getSource()).getAmount();
	}

}
