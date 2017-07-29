package com.yinzhiwu.yiwu.entity.yzw;

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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "vdepartment")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class DepartmentYzw extends BaseYzwEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2093904107877155678L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;

	@Column(length = 50, name = "Name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "superiorId", foreignKey = @ForeignKey(name = "fk_department_superior_Id", value = ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw superior;

	@Column
	private String path;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="manager1", foreignKey=@ForeignKey(
			name="fk_department_manager1", value=ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw manager1;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="manager2",foreignKey=
			@ForeignKey(name="fk_department_manager2", value = ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw manager2;

	@Column(length = 200)
	private String description;

	@Column
	private Boolean removed = Boolean.FALSE;

	@Column
	private Integer flag;

	@Column
	private Integer wparam;

	@Column
	private Integer lparam;

	@Column(length = 32)
	private String operationDistrict;

	@Column(length = 16)
	private String city;

	@Column(length = 16)
	private String officialAccount;

	@Column
	private String logo;

	@Column
	private String province;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public DepartmentYzw getSuperior() {
		return superior;
	}

	public String getPath() {
		return path;
	}

	public EmployeeYzw getManager1() {
		return manager1;
	}

	public EmployeeYzw getManager2() {
		return manager2;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getRemoved() {
		return removed;
	}

	public Integer getFlag() {
		return flag;
	}

	public Integer getWparam() {
		return wparam;
	}

	public Integer getLparam() {
		return lparam;
	}

	public String getOperationDistrict() {
		return operationDistrict;
	}

	public String getCity() {
		return city;
	}

	public String getOfficialAccount() {
		return officialAccount;
	}

	public String getLogo() {
		return logo;
	}

	public String getProvince() {
		return province;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSuperior(DepartmentYzw superior) {
		this.superior = superior;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setManager1(EmployeeYzw manager1) {
		this.manager1 = manager1;
	}

	public void setManager2(EmployeeYzw manager2) {
		this.manager2 = manager2;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public void setWparam(Integer wparam) {
		this.wparam = wparam;
	}

	public void setLparam(Integer lparam) {
		this.lparam = lparam;
	}

	public void setOperationDistrict(String operationDistrict) {
		this.operationDistrict = operationDistrict;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setOfficialAccount(String officialAccount) {
		this.officialAccount = officialAccount;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public boolean isContain(DepartmentYzw dept){
		if(dept == null) throw new IllegalArgumentException();
		if(dept.getPath().contains(this.getId().toString()))
			return true;
		return false;
	}

	// @OneToMany(mappedBy="department")
	// List<EmployeeYzw> employees = new ArrayList<>();

	// @OneToMany(mappedBy="superior")
	// List<DepartmentYzw> subordinates = new ArrayList<>();

}
