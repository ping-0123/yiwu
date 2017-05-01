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

	@Column(length=50)
	private String account;
	
	@ManyToOne
	@JoinColumn(name="capital_account_id")
	private CapitalAccountType capitalAccountType;
	

	@ManyToOne
	@JoinColumn(name="distributer_id")
	private Distributer distributer;

	

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
	
	
}
