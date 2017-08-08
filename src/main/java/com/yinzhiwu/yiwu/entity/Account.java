package com.yinzhiwu.yiwu.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.yinzhiwu.yiwu.entity.yzwOld.Employee;

@Entity
@Table(name = "yiwu_account", uniqueConstraints = {
		@UniqueConstraint(name = "uk_Account_employeeId", columnNames = "employee_id"),
		@UniqueConstraint(name = "uk_Account_account", columnNames = "account") })
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// uk
	@OneToOne
	@JoinColumn(name = "employee_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Employee employee;
	// uk
	@Column(name = "account", length = 32, nullable = false)
	private String account;

	@Column(length = 50)
	private String password;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
