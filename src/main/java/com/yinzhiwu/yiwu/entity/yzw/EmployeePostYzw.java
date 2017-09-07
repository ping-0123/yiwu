package com.yinzhiwu.yiwu.entity.yzw;

import java.util.Date;

import javax.persistence.Column;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "vemployee_post")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class EmployeePostYzw {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", 
		foreignKey=@ForeignKey(name="fk_employeePost_employee_id", value=ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw employee;
	
	@ManyToOne
	@JoinColumn(name="department_id", foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw department;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "post_id",
			foreignKey=@ForeignKey(name="fk_employeePost_post_id", value=ConstraintMode.NO_CONSTRAINT))
	private PostYzw post;

	private Date start;
	private Date end;
	@Column(name="is_default")
	private Boolean isDefault = Boolean.FALSE;
	
	@Column(name="lastChangeId")
	private Integer sf_last_change_user_id;

	@Column(name="lastChangeTime")
	private Date sf_last_change_time;

	@Column
	private Boolean removed =Boolean.FALSE;

	@Column
	private Integer machineCode;

	@Column(name="lastSyncTimestamp")
	private Date sf_last_sysn_timestamp;

	@Column(name="lastChangeTimestamp")
	private Date sf_last_change_timestamp;
	
	
	
	public void init(){
		
	}
	
	public void beforeUpdate(){
		
	}
	
	public Integer getId() {
		return id;
	}

	public EmployeeYzw getEmployee() {
		return employee;
	}

	public PostYzw getPost() {
		return post;
	}

	public Integer getSf_last_change_user_id() {
		return sf_last_change_user_id;
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

	public Date getSf_last_sysn_timestamp() {
		return sf_last_sysn_timestamp;
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

	public void setPost(PostYzw post) {
		this.post = post;
	}

	public void setSf_last_change_user_id(Integer sf_last_change_user_id) {
		this.sf_last_change_user_id = sf_last_change_user_id;
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

	public void setSf_last_sysn_timestamp(Date sf_last_sysn_timestamp) {
		this.sf_last_sysn_timestamp = sf_last_sysn_timestamp;
	}

	public void setSf_last_change_timestamp(Date sf_last_change_timestamp) {
		this.sf_last_change_timestamp = sf_last_change_timestamp;
	}

	public DepartmentYzw getDepartment() {
		return department;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public void setDepartment(DepartmentYzw department) {
		this.department = department;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}


	public Boolean isDefault() {
		return isDefault;
	}

	public void setDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	

}
