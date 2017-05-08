package com.yinzhiwu.springmvc3.model;

import com.yinzhiwu.springmvc3.entity.CapitalAccount;

public class CapitalAccountApiView {

	
	private int id;
	
	private String typeName;
	
	private String account;
	
	
	
	public CapitalAccountApiView() {
	}

	public CapitalAccountApiView(CapitalAccount c){
		this.id = c.getId();
		this.account = c.getAccount();
		this.typeName = c.getCapitalAccountType().getName();
	}
	
	public int getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
