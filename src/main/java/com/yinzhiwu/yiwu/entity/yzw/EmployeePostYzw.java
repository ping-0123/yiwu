package com.yinzhiwu.yiwu.entity.yzw;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@Entity
@Table(name = "vemployee_post")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class EmployeePostYzw extends BaseYzwEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne
	@JoinColumn(name="employee_id", 
		foreignKey=@ForeignKey(name="fk_employeePost_employee_id", value=ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw employee;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="department_id", foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw department;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "post_id",
			foreignKey=@ForeignKey(name="fk_employeePost_post_id", value=ConstraintMode.NO_CONSTRAINT))
	private PostYzw post;

	@JsonFormat(pattern="yyyy/MM/dd")
	@Column(name="start")
	private Date startTime;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="end")
	private Date endTime;
	@Column(name="is_default")
	private Boolean isDefault;
	
	@Column
	private Boolean removed;

	
	

	/* (non-Javadoc)
	 * @see com.yinzhiwu.yiwu.entity.yzw.BaseYzwEntity#init()
	 */
	@Override
	public void init() {
		super.init();
		isDefault=isDefault==null?false:isDefault;
		removed=removed==null?false:removed;
	}


	public DepartmentYzw getDepartment() {
		return department;
	}


	public void setDepartment(DepartmentYzw department) {
		this.department = department;
	}


	public Boolean isDefault() {
		return isDefault;
	}

	public void setDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}


	public Boolean getIsDefault() {
		return isDefault;
	}


	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getId() {
		return id;
	}

	public EmployeeYzw getEmployee() {
		return employee;
	}

	public Boolean getRemoved() {
		return removed;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEmployee(EmployeeYzw employee) {
		this.employee = employee;
	}

	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}
	
	public boolean isRemoved(){
		return removed;
	}

	/**
	 * @return the post
	 */
	public PostYzw getPost() {
		return post;
	}

	/**
	 * @param post the post to set
	 */
	public void setPost(PostYzw post) {
		this.post = post;
	}


	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}


	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}


	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
