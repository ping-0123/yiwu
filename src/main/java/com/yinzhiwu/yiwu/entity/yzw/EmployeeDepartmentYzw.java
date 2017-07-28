package com.yinzhiwu.yiwu.entity.yzw;

import java.util.Date;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
*@Author ping
*@Time  创建时间:2017年7月28日下午5:27:44
*
*/

@Entity
@Table(name="vemployee_department")
public class EmployeeDepartmentYzw {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", 
		foreignKey=@ForeignKey(name="fk_employeeDepartment_employee_id", value=ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw employee;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id", 
			foreignKey=@ForeignKey(name="fk_employeeDepartment_department_id", value= ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw department;
	
	private Integer sf_last_changer_user;
	private Date sf_last_change_time;
	private Boolean removed= Boolean.FALSE;
	private Integer machineCode;
	private Integer pos;
	private Integer wparam;
	private Integer lparam;
	private Date sf_last_sync_timestamp;
	private Date sf_last_change_timestamp;
	
	public void init(){
		this.sf_last_changer_user = 1;
		Date date = new Date();
		this.sf_last_change_time = date;
		this.sf_last_sync_timestamp = date;
		this.sf_last_change_timestamp = date;
	}
	
	public void beforeUpdate(){
		this.sf_last_changer_user = 1;
		Date date = new Date();
		this.sf_last_change_time = date;
		this.sf_last_change_timestamp = date;
	}
	
	
	public Integer getId() {
		return id;
	}
	public EmployeeYzw getEmployee() {
		return employee;
	}
	public DepartmentYzw getDepartment() {
		return department;
	}
	public Integer getSf_last_changer_user() {
		return sf_last_changer_user;
	}
	public Date getSf_last_change_time() {
		return sf_last_change_time;
	}
	public Boolean getRemoved() {
		return removed;
	}
	public Integer getMachineCode() {
		return machineCode;
	}
	public Integer getPos() {
		return pos;
	}
	public Integer getWparam() {
		return wparam;
	}
	public Integer getLparam() {
		return lparam;
	}
	public Date getSf_last_sync_timestamp() {
		return sf_last_sync_timestamp;
	}
	public Date getSf_last_change_timestamp() {
		return sf_last_change_timestamp;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setEmployee(EmployeeYzw employee) {
		this.employee = employee;
	}
	public void setDepartment(DepartmentYzw department) {
		this.department = department;
	}
	public void setSf_last_changer_user(Integer sf_last_changer_user) {
		this.sf_last_changer_user = sf_last_changer_user;
	}
	public void setSf_last_change_time(Date sf_last_change_time) {
		this.sf_last_change_time = sf_last_change_time;
	}
	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}
	public void setMachineCode(Integer machineCode) {
		this.machineCode = machineCode;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	public void setWparam(Integer wparam) {
		this.wparam = wparam;
	}
	public void setLparam(Integer lparam) {
		this.lparam = lparam;
	}
	public void setSf_last_sync_timestamp(Date sf_last_sync_timestamp) {
		this.sf_last_sync_timestamp = sf_last_sync_timestamp;
	}
	public void setSf_last_change_timestamp(Date sf_last_change_timestamp) {
		this.sf_last_change_timestamp = sf_last_change_timestamp;
	}
	
	
}
