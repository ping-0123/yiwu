package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="employee_id", nullable=false, unique=true)
	private int employeeId;
	
	@Column(length=32)
	private String account;
	
	@Column(length=50)
	private String password;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final int getEmployeeId() {
		return employeeId;
	}

	public final void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public final String getAccount() {
		return account;
	}

	public final void setAccount(String account) {
		this.account = account;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
