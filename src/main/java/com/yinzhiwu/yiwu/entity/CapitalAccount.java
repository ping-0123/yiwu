package com.yinzhiwu.yiwu.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.yinzhiwu.yiwu.enums.PaymentMode;
import com.yinzhiwu.yiwu.enums.PaymentMode.PaymentModeConverter;

@Entity
@Table(name = "yiwu_capital_account", uniqueConstraints = 
	@UniqueConstraint(name = "uk_CapitalAccount_accont", columnNames = "account"))
public class CapitalAccount extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7664170465208216472L;

	@Column(length = 50, nullable = false) // uk
	private String account;

	@Convert(converter=PaymentModeConverter.class)
	private PaymentMode paymentMode;

	@ManyToOne
	@JoinColumn(name = "distributer_id", foreignKey = @ForeignKey(name = "fk_CapitalAccount_distributer_id"))
	private Distributer distributer;

	public CapitalAccount() {
		super();
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
	
	
}
