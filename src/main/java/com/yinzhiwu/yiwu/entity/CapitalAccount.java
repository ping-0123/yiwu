package com.yinzhiwu.yiwu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.yinzhiwu.yiwu.enums.PaymentMode;

@Entity
@Table(name = "yiwu_capital_account", uniqueConstraints = {
	@UniqueConstraint(name = "uk_CapitalAccount_accont", columnNames = "account"),
	@UniqueConstraint(name="uk_capitalAccount_distributerId_paymentMode", columnNames={"distributer_id","paymentMode" })
})
public class CapitalAccount extends BaseEntity {

	private static final long serialVersionUID = -7664170465208216472L;

	@Column(length = 50, nullable = false) // uk
	private String account;

	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private PaymentMode paymentMode;

	@ManyToOne
	@JoinColumn(name = "distributer_id", foreignKey = @ForeignKey(name = "fk_CapitalAccount_distributer_id"))
	private Distributer distributer;

	@Column(columnDefinition="boolean not null default false")
	private Boolean isDefault;
	
	
	public CapitalAccount() {
		super();
	}

	
	
	public CapitalAccount(String account, PaymentMode paymentMode, Distributer distributer, Boolean isDefault) {
		this.account = account;
		this.paymentMode = paymentMode;
		this.distributer = distributer;
		this.isDefault = isDefault;
	}



	@Override
	public void init() {
		super.init();
		if(null == isDefault)
			isDefault=false;
	}
	


	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Distributer getDistributer() {
		return distributer;
	}

	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
	}

	/**
	 * @return the paymentMode
	 */
	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	
}
