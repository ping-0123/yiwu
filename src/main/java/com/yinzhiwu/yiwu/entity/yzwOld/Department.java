package com.yinzhiwu.yiwu.entity.yzwOld;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vdepartment")
public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;
	
	@Column(length=50, name="Name" )
	private String deptName;
	
	@Column
	private Integer superiorId;
	
	@Column
	private String path;
	
	@Column
	private Integer manager1;
	
	@Column
	private Integer manager2;
	
	@Column(length=200)
	private String description;
	
	@Column(name="sf_create_user")
	private Integer creator;
	
	@Column(name="sf_last_change_user")
	private Integer lastChanger;
	
	@Column(name="sf_create_time")
	private Date createTime;
	
	@Column(name="sf_last_change_time")
	private Date lastChangeTime;
	
	@Column
	private Integer removed;
	
	@Column
	private Integer flag;
	
	@Column
	private Integer wparam;
	
	@Column
	private Integer lparam;
	
	@Column
	private Integer machineCode;
	
	@Column(name="sf_Last_Sync_TimeStamp")
	private Date lastSyncTimeStamp;
	
	@Column(name="sf_last_change_timeStamp")
	private Date lastChangeTimeStamp;
	
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
	
	
	
	public Department() {
	}

	public Department(int id, String name) {
		this.id=id;
		this.deptName =name;
	}

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}


	public final String getDeptName() {
		return deptName;
	}

	public final void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public final Integer getSuperiorId() {
		return superiorId;
	}

	public final void setSuperiorId(Integer superiorId) {
		this.superiorId = superiorId;
	}

	public final String getPath() {
		return path;
	}

	public final void setPath(String path) {
		this.path = path;
	}

	public final Integer getManager1() {
		return manager1;
	}

	public final void setManager1(Integer manager1) {
		this.manager1 = manager1;
	}

	public final Integer getManager2() {
		return manager2;
	}

	public final void setManager2(Integer manager2) {
		this.manager2 = manager2;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final Integer getCreator() {
		return creator;
	}

	public final void setCreator(Integer creator) {
		this.creator = creator;
	}

	public final Integer getLastChanger() {
		return lastChanger;
	}

	public final void setLastChanger(Integer lastChanger) {
		this.lastChanger = lastChanger;
	}

	public final Date getCreateTime() {
		return createTime;
	}

	public final void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public final Date getLastChangeTime() {
		return lastChangeTime;
	}

	public final void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	public final int isRemoved() {
		return removed;
	}

	public final void setRemoved(int removed) {
		this.removed = removed;
	}

	public final Integer getFlag() {
		return flag;
	}

	public final void setFlag(Integer flag) {
		this.flag = flag;
	}

	public final Integer getWparam() {
		return wparam;
	}

	public final void setWparam(Integer wparam) {
		this.wparam = wparam;
	}

	public final Integer getLparam() {
		return lparam;
	}

	public final void setLparam(Integer lparam) {
		this.lparam = lparam;
	}

	public final Integer getMachineCode() {
		return machineCode;
	}

	public final void setMachineCode(Integer machineCode) {
		this.machineCode = machineCode;
	}

	public final Date getLastSyncTimeStamp() {
		return lastSyncTimeStamp;
	}

	public final void setLastSyncTimeStamp(Date lastSyncTimeStamp) {
		this.lastSyncTimeStamp = lastSyncTimeStamp;
	}

	public final Date getLastChangeTimeStamp() {
		return lastChangeTimeStamp;
	}

	public final void setLastChangeTimeStamp(Date lastChangeTimeStamp) {
		this.lastChangeTimeStamp = lastChangeTimeStamp;
	}

	public final String getOperationDistrict() {
		return operationDistrict;
	}

	public final void setOperationDistrict(String operationDistrict) {
		this.operationDistrict = operationDistrict;
	}

	public final String getCity() {
		return city;
	}

	public final void setCity(String city) {
		this.city = city;
	}

	public final String getOfficialAccount() {
		return officialAccount;
	}

	public final void setOfficialAccount(String officialAccount) {
		this.officialAccount = officialAccount;
	}

	public final String getLogo() {
		return logo;
	}

	public final void setLogo(String logo) {
		this.logo = logo;
	}

	public final String getProvince() {
		return province;
	}

	public final void setProvince(String province) {
		this.province = province;
	}
	
	
}
