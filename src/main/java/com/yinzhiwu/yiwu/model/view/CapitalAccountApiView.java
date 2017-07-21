package com.yinzhiwu.yiwu.model.view;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import com.yinzhiwu.yiwu.entity.CapitalAccount;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CapitalAccountApiView {

	@ApiModelProperty(value="分销者Id", required=true)
	@Min(value=1,message="请输入正确的分销者Id")
	private int distributerId;
	
	private int accountId;
	
	@Min(value=1, message="请输入正确的资金帐号类型Id: 10001表示微信帐号，10002表示支付宝账号")
	private int accountTypeId;
	
	private String accountTypeName;
	
	@Length(min=6, max=50)
	private String accountName;
	
	
	
	public CapitalAccountApiView() {
	}

	public CapitalAccountApiView(CapitalAccount c){
		this.distributerId = c.getDistributer().getId();
		this.accountId = c.getId();
		this.accountName = c.getAccount();
		this.accountTypeId = c.getCapitalAccountType().getId();
		this.accountTypeName = c.getCapitalAccountType().getName();
	}

	public int getDistributerId() {
		return distributerId;
	}

	public int getAccountId() {
		return accountId;
	}

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}



	
}
