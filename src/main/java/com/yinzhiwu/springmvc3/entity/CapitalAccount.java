package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="capital_account", uniqueConstraints=
	@UniqueConstraint(name="uk_CapitalAccount_accont", columnNames="account"))
public class CapitalAccount extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7664170465208216472L;

	@Column(length=50, nullable= false) //uk
	private String account;
	
	@ManyToOne
	@JoinColumn(name="capitalAccountType_id", foreignKey=@ForeignKey(name="fk_capitalAccount_capitalAccountType_id"))
	private CapitalAccountType capitalAccountType;
	

	@ManyToOne
	@JoinColumn(name="distributer_id", foreignKey=@ForeignKey(name="fk_CapitalAccount_distributer_id"))
	private Distributer distributer;

	
	private boolean isDefault;
	

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

	public CapitalAccount() {
		super();
	}


	public CapitalAccountType getCapitalAccountType() {
		return capitalAccountType;
	}

	public void setCapitalAccountType(CapitalAccountType capitalAccountType) {
		this.capitalAccountType = capitalAccountType;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	
}
