package com.yinzhiwu.yiwu.entity.yzw;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yinzhiwu.yiwu.enums.Gender;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@Entity
@Table(name = "vemployee")
@Inheritance(strategy=InheritanceType.JOINED)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeeYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6739017966853748277L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 128, name ="username", updatable=false)
	private String username;

	@Column(length = 128, name="seegle_user_id")
	private String seegleUserId;
	
	//工号
	@Column(length=10, updatable=false)
	private String number;
	
	@Column(length = 128)
	private String name;

	@Column(length = 128, name="chenk_password")
	private String password;

	@Enumerated
	@Column(name = "gender")
	private Gender gender;

	@Column(length = 20)
	private String birthday;

	@Column(length = 20)
	private String telephone;

	@Pattern(regexp = "^1\\d{10}$", message="请输入有效的11位手机号码")
	@Column(length = 20, nullable=false)
	private String cellphone;

	@Column(length = 20)
	private String fax;

	@Email
	@Column(length = 50)
	private String email;

	@Column(length=128)
	private String signature;
	@Column(length=2000)
	private String resume;
	@Column(length=128)
	private String portraitUri;
	
	@Column
	private Boolean disabled;

	@Column
	private Boolean removed;

	@Column
	private Integer wparam;

	@Column
	private Integer lparam;

	@Column(length = 128, name="seegle_user_name")
	private String seegleUserName;

	@Column(length = 128, name="seegle_serial_num")
	private String seegleSerialNum;

	@Column
	private Integer userType;

	@Column(length = 128)	
	private String accessCode;

	@Column(length = 128)
	private String bindMac;

	@Column(name="fingerprint_code")
	private String fingerprintCode;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "fk_employee_department_id"))
	private DepartmentYzw department;

	@Column(name="default_duty")
	private Integer defaultDuty;

	@Column(name="out_user")
	private Integer outUser;

	@Column(length = 32, name="cluster_server_ip")
	private String clusterServerIp;

	@Column(name="cluster_server_port")
	private Integer clusterServerPort;

	@Column(length = 32, name="cluster_token")
	private String clusterToken;

	@Column(name = "last_online_timestamp")
	private Date lastOnlineTimeStamp;
	
	
	
	@JsonIgnore
	@OneToMany(mappedBy="employee", cascade=CascadeType.REMOVE)
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<EmployeePostYzw> employeePosts = new HashSet<>();
	
//	@JsonIgnore
//	@OneToOne(mappedBy="employee",fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
//	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
//	private User user;
	
	public EmployeeYzw() {
	}


	@Override
	public void init() {
		super.init();
		if(removed== null)
			removed = Boolean.FALSE;
		if(password == null || "".equals(password.trim()))
			password = "yzw123456";
		if(disabled==null)
			disabled = Boolean.FALSE;
		if(outUser == null)
			outUser =0;
		if(username == null || "".equals(username.trim()))
			username = UUID.randomUUID().toString();
	}


	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}



	public String getSeegleUserId() {
		return seegleUserId;
	}



	public String getName() {
		return name;
	}




	public Gender getGender() {
		return gender;
	}



	public String getBirthday() {
		return birthday;
	}



	public String getTelephone() {
		return telephone;
	}



	public String getCellphone() {
		return cellphone;
	}



	public String getFax() {
		return fax;
	}



	public String getEmail() {
		return email;
	}



	public Boolean getRemoved() {
		return removed;
	}

	public boolean isRemoved(){
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



	public String getFingerprintCode() {
		return fingerprintCode;
	}



	public DepartmentYzw getDepartment() {
		return department;
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


	public Set<EmployeePostYzw> getEmployeePosts() {
		return employeePosts;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public void setSeegleUserId(String seegleUserId) {
		this.seegleUserId = seegleUserId;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setGender(Gender gender) {
		this.gender = gender;
	}



	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}



	public void setFax(String fax) {
		this.fax = fax;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public void setRemoved(Boolean removed) {
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



	public void setFingerprintCode(String fingerprintCode) {
		this.fingerprintCode = fingerprintCode;
	}



	public void setDepartment(DepartmentYzw department) {
		this.department = department;
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

	public void setEmployeePosts(Set<EmployeePostYzw> employeePosts) {
		this.employeePosts = employeePosts;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getDisabled() {
		return disabled;
	}


	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}


	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}


	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}


	public String getSignature() {
		return signature;
	}


	public String getResume() {
		return resume;
	}


	public String getPortraitUri() {
		return portraitUri;
	}


	public void setSignature(String signature) {
		this.signature = signature;
	}


	public void setResume(String resume) {
		this.resume = resume;
	}


	public void setPortraitUri(String portraitUri) {
		this.portraitUri = portraitUri;
	}


//	public User getUser() {
//		return user;
//	}
//
//
//	public void setUser(User user) {
//		this.user = user;
//	}


}
