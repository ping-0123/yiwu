package com.yinzhiwu.yiwu.entity.yzw;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Embedded;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yinzhiwu.yiwu.entity.Address;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@JsonInclude(value= Include.NON_NULL)
@Entity
@Table(name = "vdepartment")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Where(clause="dataStatus <> 2")
public class DepartmentYzw extends BaseYzwEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2093904107877155678L;
	
	public static enum OrgnizationType{
		STORE,
		DISTRICT,
		OPERATING,
		MARKET,
		HR,
		FINANCE,
		SUBCOMPANY,
		COMPANY
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;

	@Column(length = 50, name = "Name")
	private String name;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "superiorId", foreignKey = @ForeignKey(name = "fk_department_superior_Id", value = ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw parent;

	@JsonIgnore
	@OneToMany(mappedBy="parent")
	private List<DepartmentYzw> children = new ArrayList<>();
	
	@Column
	private String path;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="manager1", foreignKey=@ForeignKey(
			name="fk_department_manager1", value=ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw manager1;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="manager2",foreignKey=
			@ForeignKey(name="fk_department_manager2", value = ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw manager2;

	@Column(length = 200)
	private String description;

	@Column
	private Boolean removed;

	@Column
	private Integer flag;

	@Column
	private Integer wparam;

	@Column
	private Integer lparam;

	@Column(length = 32)
	private String operationDistrict;

	@Column(length = 16)
	private String officialAccount;

	@Column
	private String logo;
	
	@Embedded
	private Address officialAddress;
	
	@Override
	public void init(){
		super.init();
		removed=removed==null?Boolean.FALSE:removed;
		path=path==null?"":path;
	}
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
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

	public String getOfficialAccount() {
		return officialAccount;
	}

	public String getLogo() {
		return logo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setOfficialAccount(String officialAccount) {
		this.officialAccount = officialAccount;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isContain(DepartmentYzw dept){
		if(dept == null) throw new IllegalArgumentException();
		if(dept.getPath().contains(this.getId().toString()))
			return true;
		return false;
	}

	public Address getOfficialAddress() {
		return officialAddress;
	}

	public void setOfficialAddress(Address officialAddress) {
		this.officialAddress = officialAddress;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DepartmentYzw)) {
			return false;
		}
		DepartmentYzw other = (DepartmentYzw) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the parent
	 */
	public DepartmentYzw getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(DepartmentYzw parent) {
		this.parent = parent;
	}

	/**
	 * @return the children
	 */
	public List<DepartmentYzw> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<DepartmentYzw> children) {
		this.children = children;
	}
	
}
