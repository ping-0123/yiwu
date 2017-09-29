package com.yinzhiwu.yiwu.entity.sys;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import com.yinzhiwu.yiwu.entity.BaseEntity;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;

@Entity
@Table(name="sys_user", uniqueConstraints={
		@UniqueConstraint(name="uk_user_username", columnNames={"username"})
})
@Where(clause="dataStatus<>2")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2447505510450864096L;

	@Column(length=32, updatable=false)
	private String username;
	
	@Column(length=32)
	private String password;
	
	@Column(length=32)
	private String salt;
	
	@OneToOne
	@JoinColumn(foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT, name="fk_user_employee_id"))
	private EmployeeYzw employee;
	
	@ManyToMany
	@JoinTable(name="sys_user_role", 
		joinColumns=@JoinColumn(name="user_id", foreignKey=@ForeignKey(name="fk_userRole_user_id")),
		inverseJoinColumns=@JoinColumn(name="role_id", foreignKey=@ForeignKey(name="fk_userRole_role_id", value=ConstraintMode.NO_CONSTRAINT)))
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<Role> roles = new HashSet<Role>();

	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * @return the employee
	 */
	public EmployeeYzw getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(EmployeeYzw employee) {
		this.employee = employee;
	}

	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getCredentialsSalt() {
		return username+salt;
	} 
	
	
	
}
