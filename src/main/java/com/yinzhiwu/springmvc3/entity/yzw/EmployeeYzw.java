package com.yinzhiwu.springmvc3.entity.yzw;

import java.util.Date;

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


@Entity
@Table(name="vemployee")
public class EmployeeYzw extends BaseYzwEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6739017966853748277L;
	
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
	
	
	@Column
	private Integer removed;
	
	@Column
	private Integer wparam;
	
	@Column
	private Integer lparam;
	
	
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
	
		
	@Column
	private String fingerPrintNo;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="departmentId", foreignKey=
			@ForeignKey(value=ConstraintMode.NO_CONSTRAINT, name="fk_employee_department_id"))
	private DepartmentYzw department;
	
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

	
	public EmployeeYzw() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public String getSeegleUserId() {
		return seegleUserId;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Integer getGender() {
		return gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getTel() {
		return tel;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public String getFax() {
		return fax;
	}

	public String getEmail() {
		return email;
	}

	public Integer getDisabled() {
		return disabled;
	}


	public Integer getRemoved() {
		return removed;
	}

	public Integer getWparam() {
		return wparam;
	}

	public Integer getLparam() {
		return lparam;
	}

	public String getSeegleUserName() {
		return seegleUserName;
	}

	public String getSeegleSerialNum() {
		return seegleSerialNum;
	}

	public Integer getUserType() {
		return userType;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public String getBindMac() {
		return bindMac;
	}

	public String getFingerPrintNo() {
		return fingerPrintNo;
	}


	public Integer getDefaultDuty() {
		return defaultDuty;
	}

	public Integer getOutUser() {
		return outUser;
	}

	public String getClusterServerIp() {
		return clusterServerIp;
	}

	public Integer getClusterServerPort() {
		return clusterServerPort;
	}

	public String getClusterToken() {
		return clusterToken;
	}

	public Date getLastOnlineTimeStamp() {
		return lastOnlineTimeStamp;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setSeegleUserId(String seegleUserId) {
		this.seegleUserId = seegleUserId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public void setRemoved(Integer removed) {
		this.removed = removed;
	}

	public void setWparam(Integer wparam) {
		this.wparam = wparam;
	}

	public void setLparam(Integer lparam) {
		this.lparam = lparam;
	}

	public void setSeegleUserName(String seegleUserName) {
		this.seegleUserName = seegleUserName;
	}

	public void setSeegleSerialNum(String seegleSerialNum) {
		this.seegleSerialNum = seegleSerialNum;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public void setBindMac(String bindMac) {
		this.bindMac = bindMac;
	}

	public void setFingerPrintNo(String fingerPrintNo) {
		this.fingerPrintNo = fingerPrintNo;
	}


	public void setDefaultDuty(Integer defaultDuty) {
		this.defaultDuty = defaultDuty;
	}

	public void setOutUser(Integer outUser) {
		this.outUser = outUser;
	}

	public void setClusterServerIp(String clusterServerIp) {
		this.clusterServerIp = clusterServerIp;
	}

	public void setClusterServerPort(Integer clusterServerPort) {
		this.clusterServerPort = clusterServerPort;
	}

	public void setClusterToken(String clusterToken) {
		this.clusterToken = clusterToken;
	}

	public void setLastOnlineTimeStamp(Date lastOnlineTimeStamp) {
		this.lastOnlineTimeStamp = lastOnlineTimeStamp;
	}



	public DepartmentYzw getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentYzw department) {
		this.department = department;
	}
	
	
	

}
