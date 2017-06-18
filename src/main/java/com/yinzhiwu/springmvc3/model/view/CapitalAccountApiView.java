package com.yinzhiwu.springmvc3.model.view;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.yinzhiwu.springmvc3.entity.CapitalAccount;

public class CapitalAccountApiView {

	
	@Min(1)
	private int distributerId;
	
	@Min(1)
	private int accountId;
	
	@Pattern(regexp = "(微信|支付宝)")
	private String typeName;
	
	
	@Length(min=6, max=50)
	private String account;
	
	
	
	public CapitalAccountApiView() {
	}

	public CapitalAccountApiView(CapitalAccount c){
		this.distributerId = c.getDistributer().getId();
		this.accountId = c.getId();
		this.account = c.getAccount();
		this.typeName = c.getCapitalAccountType().getName();
	}

	public String getAccount() {
		return account;
	}

	public String getTypeName() {
		return typeName;
	}


	public void setAccount(String account) {
		this.account = account;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getDistributerId() {
		return distributerId;
	}

	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}
	
	
}
