package com.yinzhiwu.springmvc3.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vemployee")
public class Employee {

	
	@Id
	@Column(length=32)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=128)
	private String user;
	
	@Column(length=128)
	private String seegleUserId;
	
	@Column(length=128)
	private String name;
	
	@Column(length=128)
	private String password;
	
	@Column(name="Sex")
	private Integer gender;
	
	@Column(length=20)
	private String birthday;
	
	@Column(length=20)
	private String tel;
	
	@Column(length=20)
	private String cellPhone;
	
	@Column(length=20)
	private String fax;
	
	@Column(length=50)
	private String email;
	
	@Column
	private Integer disabled;
	
	@Column(name="sf_create_user")
	private Integer creator;
	
	@Column(name="sf_last_change_user")
	private Integer lastChanger;
	
	@Column
	private Date createTime;
	
	@Column(name="sf_last_change_time")
	private Date lastChangeTime;
	
	@Column
	private Integer removed;
	
	@Column
	private Integer wparam;
	
	@Column
	private Integer lparam;
	
	@Column
	private Integer machineCode;
	
	@Column(length=128)
	private String seegleUserName;
	
	@Column(length=128)
	private String seegleSerialNum;
	
	@Column
	private Integer userType;
	
	@Column(length=128)
	private String accessCode;
	
	@Column(length=128)
	private String bindMac;
	
	@Column(name="sf_Last_Sync_TimeStamp")
	private Date lastSyncTimeStamp;
	
	@Column(name="sf_last_change_timeStamp")
	private Date lastChangeTimeStamp;
	
	@Column
	private String fingerPrintNo;
	
	@Column
	private Integer departmentId;
	
	@Column
	private Integer defaultDuty;
	
	@Column
	private Integer outUser;
	
	@Column(length=32)
	private String clusterServerIp;
	
	@Column
	private Integer clusterServerPort;
	
	@Column(length=32)
	private String clusterToken;
	
	@Column(name="Last_Online_TimeStamp")
	private Date lastOnlineTimeStamp;
	
	
	

	public Employee() {
	}

	
	public Employee(Integer id, String name) {
		this.id = id;
		this.name = name;
	}


	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getUser() {
		return user;
	}

	public final void setUser(String user) {
		this.user = user;
	}

	public final String getSeegleUserId() {
		return seegleUserId;
	}

	public final void setSeegleUserId(String seegleUserId) {
		this.seegleUserId = seegleUserId;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final Integer getGender() {
		return gender;
	}

	public final void setGender(Integer gender) {
		this.gender = gender;
	}

	public final String getBirthday() {
		return birthday;
	}

	public final void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public final String getTel() {
		return tel;
	}

	public final void setTel(String tel) {
		this.tel = tel;
	}

	public final String getCellPhone() {
		return cellPhone;
	}

	public final void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public final String getFax() {
		return fax;
	}

	public final void setFax(String fax) {
		this.fax = fax;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final Integer getDisabled() {
		return disabled;
	}

	public final void setDisabled(Integer disabled) {
		this.disabled = disabled;
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

	public final Integer getRemoved() {
		return removed;
	}

	public final void setRemoved(Integer removed) {
		this.removed = removed;
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

	public final String getSeegleUserName() {
		return seegleUserName;
	}

	public final void setSeegleUserName(String seegleUserName) {
		this.seegleUserName = seegleUserName;
	}

	public final String getSeegleSerialNum() {
		return seegleSerialNum;
	}

	public final void setSeegleSerialNum(String seegleSerialNum) {
		this.seegleSerialNum = seegleSerialNum;
	}

	public final Integer getUserType() {
		return userType;
	}

	public final void setUserType(Integer userType) {
		this.userType = userType;
	}

	public final String getAccessCode() {
		return accessCode;
	}

	public final void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public final String getBindMac() {
		return bindMac;
	}

	public final void setBindMac(String bindMac) {
		this.bindMac = bindMac;
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

	public final String getFingerPrintNo() {
		return fingerPrintNo;
	}

	public final void setFingerPrintNo(String fingerPrintNo) {
		this.fingerPrintNo = fingerPrintNo;
	}

	public final Integer getDepartmentId() {
		return departmentId;
	}

	public final void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public final Integer getDefaultDuty() {
		return defaultDuty;
	}

	public final void setDefaultDuty(Integer defaultDuty) {
		this.defaultDuty = defaultDuty;
	}

	public final Integer getOutUser() {
		return outUser;
	}

	public final void setOutUser(Integer outUser) {
		this.outUser = outUser;
	}

	public final String getClusterServerIp() {
		return clusterServerIp;
	}

	public final void setClusterServerIp(String clusterServerIp) {
		this.clusterServerIp = clusterServerIp;
	}


	public final Integer getClusterServerPort() {
		return clusterServerPort;
	}

	public final void setClusterServerPort(Integer clusterServerPort) {
		this.clusterServerPort = clusterServerPort;
	}

	public final String getClusterToken() {
		return clusterToken;
	}

	public final void setClusterToken(String clusterToken) {
		this.clusterToken = clusterToken;
	}

	public final Date getLastOnlineTimeStamp() {
		return lastOnlineTimeStamp;
	}

	public final void setLastOnlineTimeStamp(Date lastOnlineTimeStamp) {
		this.lastOnlineTimeStamp = lastOnlineTimeStamp;
	}


	public Date getLastChangeTime() {
		return lastChangeTime;
	}


	public void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}
	
	
	
}
