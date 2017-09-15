package com.yinzhiwu.yiwu.entity.yzw;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.enums.Gender;

@Entity
@Table(name = "vemployee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Where(clause="removed=false")
public class EmployeeYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6739017966853748277L;

	@Id
	@Column(length = 32)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 128, name ="username")
	private String username;

	@Column(length = 128, name="seegle_user_id")
	private String seegleUserId;

	@Column(length = 128)
	private String name;

	@Column(length = 128, name="chenk_password")
	private String chenkPassword;

	@Enumerated
	@Column(name = "gender")
	private Gender gender;

	@Column(length = 20)
	private String birthday;

	@Column(length = 20)
	private String telephone;

	@Column(length = 20)
	private String cellphone;

	@Column(length = 20)
	private String fax;

	@Column(length = 50)
	private String email;

	@Column
	private Integer disabled;

	@Column
	private Boolean removed = Boolean.FALSE;

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

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
	
	@Column(length=128, name="yiwu_password")
	private String password;
	@Column(length=123)
	private String salt;
	
	@OneToMany
	@JoinColumn(name="employee_id", foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<EmployeePostYzw> employeePosts = new LinkedHashSet<>();
	
	public EmployeeYzw() {
	}

	public String getCredentialsSalt() {
		return this.username + this.salt;
	}

	public Set<RoleYzw> getRoles(){
		Set<RoleYzw> roles = new LinkedHashSet<>();
		Set<EmployeePostYzw> empPosts = this.getEmployeePosts();
		for (EmployeePostYzw ep : empPosts) {
			roles.add(ep.getRole());
		}
		return roles;
	}
	
	public Set<Resource> getResources(){
		Set<Resource> resources = new LinkedHashSet<>();
		for(RoleYzw role: getRoles()){
			resources.addAll(role.getResources());
		}
		return resources;
	}
	
	
	public Set<String> getStringRoles(){
		Set<String> stringRoles = new LinkedHashSet<>();
		for(RoleYzw role:getRoles()){
			stringRoles.add(role.getName());
		}
		
		return stringRoles;
	}
	
	public Set<String> getStringPermissions(){
		Set<String> permissions = new LinkedHashSet<>();
		for(Resource r: getResources()){
			permissions.add(r.getPermission());
		}
		return permissions;
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



	public String getChenkPassword() {
		return chenkPassword;
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



	public Integer getDisabled() {
		return disabled;
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



	public String getPassword() {
		return password;
	}



	public String getSalt() {
		return salt;
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



	public void setChenkPassword(String chenkPassword) {
		this.chenkPassword = chenkPassword;
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



	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
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



	public void setPassword(String password) {
		this.password = password;
	}



	public void setSalt(String salt) {
		this.salt = salt;
	}


	public void setEmployeePosts(Set<EmployeePostYzw> employeePosts) {
		this.employeePosts = employeePosts;
	}
}
