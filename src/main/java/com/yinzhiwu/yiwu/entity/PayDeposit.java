package com.yinzhiwu.yiwu.entity;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.yinzhiwu.yiwu.enums.IncomeType;
import com.yinzhiwu.yiwu.event.IncomeEvent;
import com.yinzhiwu.yiwu.event.IncomeEventType;

/**
*@Author ping
*@Time  创建时间:2017年11月10日上午9:25:36
*
*/

@SuppressWarnings("serial")
public class PayDeposit extends BaseEntity implements IncomeEvent{
	
	@NotNull
	private Distributer distributer;
	
	@DecimalMin(value="0.01")
	private Float amount;
	
	@NotNull
	private IncomeType payMethod;
	
	private Date date;

	@Override
	public void init() {
		super.init();
		date = super.getCreateTime();
		if(null == amount)
			amount = 0f;
	}
	
	public Distributer getDistributer() {
		return distributer;
	}

	public Float getAmount() {
		return amount;
	}

	public IncomeType getPayMethod() {
		return payMethod;
	}

	public Date getDate() {
		return date;
	}

	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public void setPayMethod(IncomeType payMethod) {
		this.payMethod = payMethod;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public IncomeEventType getType() {
		switch (getPayMethod()) {
		case BROKERAGE:
			return IncomeEventType.PAY_DEPOSIT_BY_BROKERAGE;
		case FUNDS:
			return IncomeEventType.PAY_DEPOSIT_BY_FUNDS;
		default:
			throw new UnsupportedOperationException("除佣金和基金以外，其他的收益均不能转换为定金");
		}
	}

	@Override
	public Object getSource() {
		return this;
	}

	@Override
	public String getSourceId() {
		return getId().toString();
	}

	@Override
	public Object getSubject() {
		return getDistributer();
	}

	@Override
	public Float getValue() {
		return getAmount();
	}

	
}
