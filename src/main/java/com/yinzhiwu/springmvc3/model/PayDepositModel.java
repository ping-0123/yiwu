package com.yinzhiwu.springmvc3.model;

import javax.validation.constraints.Min;

public class PayDepositModel {
	
	@Min(1)
	private int distributerId;
	
	@Min(0)
	private float amount;
	
	private boolean fundsFisrt;

	public int getDistributerId() {
		return distributerId;
	}

	public float getAmount() {
		return amount;
	}

	public boolean isFundsFisrt() {
		return fundsFisrt;
	}

	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setFundsFisrt(boolean fundsFisrt) {
		this.fundsFisrt = fundsFisrt;
	}
	
	

}
