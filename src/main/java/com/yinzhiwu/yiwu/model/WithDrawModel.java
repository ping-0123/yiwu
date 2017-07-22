package com.yinzhiwu.yiwu.model;

import javax.validation.constraints.Min;

public class WithDrawModel {

	

	@Min(1)
	private int distributerId;
	
	@Min(1)
	private int accountId;
	
	@Min(0)
	private float amount;
	

	public WithDrawModel() {
	}
	
	
	public WithDrawModel(int distributerId, int accountid, float amount) {
		this.distributerId = distributerId;
		this.accountId = accountid;
		this.amount = amount;
	}
	

	public int getDistributerId() {
		return distributerId;
	}

	public int getAccountId() {
		return accountId;
	}

	public float getAmount() {
		return amount;
	}

	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}

	public void setAccountId(int accountid) {
		this.accountId = accountid;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}


	
}
