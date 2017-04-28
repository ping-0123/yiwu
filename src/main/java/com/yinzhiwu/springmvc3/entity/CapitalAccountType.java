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
	
	
	private List<CapitalAccount> capitalAccounts = new ArrayList<>();


	@OneToMany(mappedBy="capitalAccountType")
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
