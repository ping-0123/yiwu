package com.yinzhiwu.springmvc3.entity.type;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.yinzhiwu.springmvc3.entity.CapitalAccount;

@Entity
@DiscriminatorValue("capitalAccountType")
public class CapitalAccountType extends BaseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8530666072419354311L;
	
	public static final CapitalAccountType WECHAT_PAY = new CapitalAccountType("WECHAT_PAY");
	public static final CapitalAccountType ALI_PAY = new CapitalAccountType("ALI_PAY");
	
	
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


	public CapitalAccountType(String name) {
		super(name);
	}
	
	

}
