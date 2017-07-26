package com.yinzhiwu.yiwu.model;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CapitalAccountModel {

	@ApiModelProperty(value = "资金帐号：如支付宝账号:longfreebird@163.com")
	@Length(min = 6, max = 50)
	private String accountName;

	@Min(value = 1, message = "请输入正确的资金帐号类型Id: 10001表示微信帐号，10002表示支付宝账号")
	private int capitalAccountTypeId;

	@Min(value = 1, message = "请输入正确的分销者Id")
	private int distributerId;

	public String getAccountName() {
		return accountName;
	}

	public int getCapitalAccountTypeId() {
		return capitalAccountTypeId;
	}

	public int getDistributerId() {
		return distributerId;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setCapitalAccountTypeId(int capitalAccountTypeId) {
		this.capitalAccountTypeId = capitalAccountTypeId;
	}

	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}

}
