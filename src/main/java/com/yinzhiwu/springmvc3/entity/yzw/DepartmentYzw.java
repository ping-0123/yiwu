package com.yinzhiwu.springmvc3.entity.yzw;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="vdepartment")
public class DepartmentYzw extends BaseYzwEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2093904107877155678L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;
	
	@Column(length=50, name="Name" )
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="superiorId", foreignKey=
			@ForeignKey(name="fk_department_superior_Id", value=ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw superior;
	
	@Column
	private String path;
	
	@Column
	private Integer manager1;
	
	@Column
	private Integer manager2;
	
	@Column(length=200)
	private String description;
	


	@Column
	private Integer removed;
	
	@Column
	private Integer flag;
	
	@Column
	private Integer wparam;
	
	@Column
	private Integer lparam;
	

		
	@Column(length=32)
	private String operationDistrict;
	
	@Column(length=16)
	private String city;
	
	@Column(length=16)
	private String officialAccount;
	
	@Column
	private String logo;
	
	@Column
	private String province;
	
//	@OneToMany(mappedBy="department")
//	List<EmployeeYzw> employees = new ArrayList<>();
	
//	@OneToMany(mappedBy="superior")
//	List<DepartmentYzw> subordinates = new ArrayList<>();
	
}
