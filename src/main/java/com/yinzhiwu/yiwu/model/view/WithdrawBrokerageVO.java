package com.yinzhiwu.yiwu.model.view;

import com.yinzhiwu.yiwu.entity.WithdrawBrokerage;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

import io.swagger.annotations.ApiModel;

/**
 * 
 * 
 * @author ping
 * @Date 2017年10月29日 下午9:29:02
 *
 */

@MapedClass(WithdrawBrokerage.class)
@ApiModel("体现记录VO")
public class WithdrawBrokerageVO {
	
	private Integer id;
	
	@MapedProperty("distributer.id")
	private Integer distributerId;
	
	@MapedProperty("distributer.name")
	private String distributerName;
	
	private Float amount;
	
	private Boolean payed;
	
	@MapedProperty("capitalAccount.account")
	private String capitalAccount;
	
	@MapedProperty("account.paymentMode")
	private String capitalAccountType;

	
	public static final  class WithdrawBrokerageVOConverter extends AbstractConverter<WithdrawBrokerage, WithdrawBrokerageVO>{
		public static final WithdrawBrokerageVOConverter INSTANCE = new WithdrawBrokerageVOConverter();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDistributerId() {
		return distributerId;
	}

	public void setDistributerId(Integer distributerId) {
		this.distributerId = distributerId;
	}

	public String getDistributerName() {
		return distributerName;
	}

	public void setDistributerName(String distributerName) {
		this.distributerName = distributerName;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Boolean getPayed() {
		return payed;
	}

	public void setPayed(Boolean payed) {
		this.payed = payed;
	}

	public String getCapitalAccount() {
		return capitalAccount;
	}

	public void setCapitalAccount(String capitalAccount) {
		this.capitalAccount = capitalAccount;
	}

	public String getCapitalAccountType() {
		return capitalAccountType;
	}

	public void setCapitalAccountType(String capitalAccountType) {
		this.capitalAccountType = capitalAccountType;
	}
	
	
}
