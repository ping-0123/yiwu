package com.yinzhiwu.springmvc3.model;

import javax.validation.constraints.Min;

public class WithDrawModel {

	

	@Min(1)
	private int distributerId;
	
	@Min(1)
	private int accountid;
	
	@Min(0)
	private float amount;
	

	public WithDrawModel() {
	}
	
	
	public WithDrawModel(int distributerId, int accountid, float amount) {
		this.distributerId = distributerId;
		this.accountid = accountid;
		this.amount = amount;
	}
	

	public int getDistributerId() {
		return distributerId;
	}

	public int getAccountid() {
		return accountid;
	}

	public float getAmount() {
		return amount;
	}

	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}


	
}
