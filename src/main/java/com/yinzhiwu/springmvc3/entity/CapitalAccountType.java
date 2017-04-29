package com.yinzhiwu.springmvc3.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("capitalAccountType")
public class CapitalAccountType extends BaseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8530666072419354311L;
	
	@OneToMany(mappedBy="capitalAccountType")
	private List<CapitalAccount> capitalAccounts = new ArrayList<>();



	public List<CapitalAccount> getCapitalAccounts() {
		return capitalAccounts;
	}


	public void setCapitalAccounts(List<CapitalAccount> capitalAccounts) {
		this.capitalAccounts = capitalAccounts;
	}


	public CapitalAccountType() {
		super();
	}
	
	

}
