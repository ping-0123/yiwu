package com.yinzhiwu.yiwu.model;

import javax.validation.constraints.Min;

public class PayDepositModel {
	
	@Min(1)
	private int distributerId;
	
	@Min(0)
	private float amount;
	
	private boolean fundsFirst;

	public int getDistributerId() {
		return distributerId;
	}

	public float getAmount() {
		return amount;
	}


	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public boolean isFundsFirst() {
		return fundsFirst;
	}

	public void setFundsFirst(boolean fundsFirst) {
		this.fundsFirst = fundsFirst;
	}

	

}
