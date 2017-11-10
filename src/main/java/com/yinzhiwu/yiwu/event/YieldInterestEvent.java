package com.yinzhiwu.yiwu.event;

import org.springframework.context.ApplicationEvent;

import com.yinzhiwu.yiwu.entity.income.DistributerIncome;

/**
*@Author ping
*@Time  创建时间:2017年11月10日下午6:13:50
*
*/

@SuppressWarnings("serial")
public class YieldInterestEvent extends ApplicationEvent implements IncomeEvent{

	public YieldInterestEvent(DistributerIncome brokerageIncome) {
		super(brokerageIncome);
	}

	@Override
	public IncomeEventType getType() {
		return IncomeEventType.YIELD_INTEREST;
	}

	@Override
	public String getSourceId() {
		return ((DistributerIncome) getSource()).getId().toString();
	}

	@Override
	public Object getSubject() {
		return ((DistributerIncome) getSource()).getDistributer();
	}

	@Override
	public Float getValue() {
		return ((DistributerIncome) getSource()).getValue();
	}

}
