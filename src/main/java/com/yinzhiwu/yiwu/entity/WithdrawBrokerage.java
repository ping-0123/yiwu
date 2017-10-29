package com.yinzhiwu.yiwu.entity;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name="yiwu_withdraw_brokerage")
public class WithdrawBrokerage extends BaseEntity{
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_withdrawBrokerage_distributer_id"))
	private Distributer distributer;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_withdrawBrokerage_capitalAccount_id", value = ConstraintMode.NO_CONSTRAINT))
	private CapitalAccount capitalAccount;
	
	@NotNull
	@Min(0)
	private Float amount;
	
	private Boolean payed;
	
	@Override
	public void init() {
		super.init();
		payed=false;
	}

	/**
	 * @return the distributer
	 */
	public Distributer getDistributer() {
		return distributer;
	}

	/**
	 * @param distributer the distributer to set
	 */
	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
	}

	/**
	 * @return the capitalAccount
	 */
	public CapitalAccount getCapitalAccount() {
		return capitalAccount;
	}

	/**
	 * @param capitalAccount the capitalAccount to set
	 */
	public void setCapitalAccount(CapitalAccount capitalAccount) {
		this.capitalAccount = capitalAccount;
	}

	/**
	 * @return the amount
	 */
	public Float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Float amount) {
		this.amount = amount;
	}

	/**
	 * @return the payed
	 */
	public Boolean getPayed() {
		return payed;
	}

	/**
	 * @param payed the payed to set
	 */
	public void setPayed(Boolean payed) {
		this.payed = payed;
	}

	/* (non-Javadoc)
	 * @see com.yinzhiwu.yiwu.entity.BaseEntity#init()
	 */
	
	
	
}
