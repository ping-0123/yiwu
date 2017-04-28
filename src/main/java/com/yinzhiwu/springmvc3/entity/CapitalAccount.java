package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="capital_account")
public class CapitalAccount extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7664170465208216472L;

	private String account;
	

	private CapitalAccountType capitalAccountType;
	

	private Distributer distributer;

	
	@Column(length=50)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}



	@ManyToOne
	@JoinColumn(name="distributer_id")
	public Distributer getDistributer() {
		return distributer;
	}

	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
	}

	public CapitalAccount() {
		super();
	}

	@ManyToOne
	@JoinColumn(name="capital_account_id")
	public CapitalAccountType getCapitalAccountType() {
		return capitalAccountType;
	}

	public void setCapitalAccountType(CapitalAccountType capitalAccountType) {
		this.capitalAccountType = capitalAccountType;
	}
	
	
}
