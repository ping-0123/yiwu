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
import org.hibernate.annotations.Where;

@Entity
@Table(name = "vemployee_post")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Where(clause="dataStatus <> 2")
public class EmployeePostYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 247786828073130079L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", 
		foreignKey=@ForeignKey(name="fk_employeePost_employee_id", value=ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw employee;
	
	@ManyToOne(fetch=FetchType.LAZY)
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
	
	@Column
	private Boolean removed =Boolean.FALSE;



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

}
