package com.yinzhiwu.yiwu.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.enums.Gender;
import com.yinzhiwu.yiwu.model.DistributerRegisterModel;

/**
 * 
 * @author ping
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "yiwu_distributer", uniqueConstraints = {
		@UniqueConstraint(name = "uk_distributer_memberId", columnNames = { "memberId" }),
		@UniqueConstraint(name = "uk_distributer_account", columnNames = { "username" }),
		@UniqueConstraint(name = "uk_distributer_wechatNo", columnNames = "wechatNO"),
		@UniqueConstraint(name = "uk_distributer_phoneNo", columnNames = "phoneNo"),
		@UniqueConstraint(name = "uk_distributer_shareCode", columnNames = "shareCode"),
		// @UniqueConstraint(name="uk_distributer_headIconName",
		// columnNames="headIconName"),
		@UniqueConstraint(name = "fuk_distributer_customer_id", columnNames = "customer_id"),
		@UniqueConstraint(name = "fuk_distributer_defaultCapitalAccount_id", columnNames = "defaultCapitalAccount_id") })
public class Distributer extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8400038437062433347L;

	/**
	 * unique=true, @Formula("concat('E5',
	 * lpad(id,8,'0'))") @ColumnDefault("concat('E5', lpad(id,8,'0'))")
	 */
	@Column(length = 32, nullable = false, updatable = false)
	private String memberId;

	@Column(length = 32)
	@Size(min = 2, max = 32)
	private String name;

	@Size(min = 2, max = 32)
	@Column(length = 32)
	private String nickName; // 默认是会员Id

	/**
	 * @Pattern(regexp="(\\p{Alpha}|\\d|_){5,31}") @NotNull unique
	 */

	@Column(length = 32, insertable = true, updatable = false)
	private String username; // 默认是手机号

	@Column(length = 32, nullable = false)
	private String password;

	/**
	 * unique
	 */
	@NotNull
	@Column(length = 32, nullable = false)
	private String wechatNo;

	/**
	 * unique
	 */
	@Pattern(regexp = "^1\\d{10}$")
	@Column(length = 32, nullable = false)
	private String phoneNo;

	// @Past
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	// @NotNull
	@Column(length = 10)
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;

	// unique
	@Column(length = 10, updatable = false)
	private String shareCode;

	@ManyToOne(targetEntity = Distributer.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "super_distributer_id", foreignKey = @ForeignKey(name = "fk_distributer_superDistributer_id"))
	private Distributer superDistributer;

	// unique
	@Column(length = 255, name = "headIconUrl")
	private String headIconName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date registedTime;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "followedByStore_id", foreignKey = @ForeignKey(name = "fk_distributer_followedByStore_id", value = ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw followedByStore;

	// unique
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_distributer_defaultCapitalAccount_id"))
	private CapitalAccount defaultCapitalAccount;

	/**
	 * 根据手机号码 或者微信号做唯一性关联
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_distributer_customer_id", value = ConstraintMode.NO_CONSTRAINT))
	private CustomerYzw customer;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_distributer_employee_id", value = ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw employee;

	@JsonIgnore
	@OneToMany(mappedBy = "superDistributer")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Distributer> subordinates = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "distributer")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<CapitalAccount> capitalAccounts = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "receiver")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Message> messages = new ArrayList<>();

	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JsonIgnore
	@OneToMany(mappedBy = "distributer", cascade = CascadeType.PERSIST)
	private List<DistributerIncome> distributerIncomes = new ArrayList<>();

	@Override
	public void init() {
		super.init();
		if (!StringUtils.hasLength(this.getName()))
			setName(getPhoneNo());
		if (!StringUtils.hasLength(getUsername()))
			setUsername(getPhoneNo());
		if (!StringUtils.hasLength(getNickName()))
			setNickName(getPhoneNo());
		if (!StringUtils.hasLength(getPassword()))
			this.password = "yzw123456";
		this.registedTime = super.getCreateDate();

	}

	public Distributer() {
	}

	public void initialize() {
		if (StringUtils.isEmpty(this.username)) {
			this.username = this.phoneNo;
		}
	}

	public Distributer(DistributerRegisterModel m) {
		super();
		this.registedTime = getCreateDate();
		this.phoneNo = m.getPhoneNo();
		this.username = m.getUsername();
		this.password = m.getPassword();
		this.name = m.getName();
		this.nickName = m.getNickName();
		this.gender = m.getGender();
		this.birthday = m.getBirthDay();
		DepartmentYzw d = new DepartmentYzw();
		d.setId(m.getFollowedByStoreId());
		this.setFollowedByStore(d);
		this.wechatNo = m.getWechatNo();
	}

	public int getAge() {
		Calendar today = Calendar.getInstance();
		Calendar bir = Calendar.getInstance();
		bir.setTime(birthday);
		long age = (today.getTimeInMillis() - bir.getTimeInMillis()) / (1000 * 3600 * 24 * 365);
		return (int) age;
	}

	public boolean isAudit() {
		if (getAge() >= 18)
			return true;
		else
			return false;
	}

	public Float getIncomeValue(IncomeType type) {
		List<DistributerIncome> incomes = this.getDistributerIncomes();
		if (null == incomes || incomes.size() == 0)
			return null;

		for (DistributerIncome income : incomes) {
			if (type.equals(income.getIncomeType()))
				return income.getIncome();
		}
		return 0f;
	}

	public DistributerIncome getDistributerIncome(IncomeType type) {
		List<DistributerIncome> incomes = this.getDistributerIncomes();
		if (null == incomes || incomes.size() == 0)
			return null;

		for (DistributerIncome income : incomes) {
			if (type.equals(income.getIncomeType()))
				return income;
		}
		return null;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public String getHeadIconName() {
		return headIconName;
	}

	public void setHeadIconName(String headIconName) {
		this.headIconName = headIconName;
	}

	public String getName() {
		return name;
	}

	public String getNickName() {
		return nickName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getWechatNo() {
		return wechatNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public Distributer getSuperDistributer() {
		return superDistributer;
	}

	public Date getRegistedTime() {
		return registedTime;
	}

	public DepartmentYzw getFollowedByStore() {
		return followedByStore;
	}

	public CapitalAccount getDefaultCapitalAccount() {
		return defaultCapitalAccount;
	}

	public CustomerYzw getCustomer() {
		return customer;
	}

	public EmployeeYzw getEmployee() {
		return employee;
	}

	public List<Distributer> getSubordinates() {
		return subordinates;
	}

	public Set<CapitalAccount> getCapitalAccounts() {
		return capitalAccounts;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public List<DistributerIncome> getDistributerIncomes() {
		return distributerIncomes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setSuperDistributer(Distributer superDistributer) {
		this.superDistributer = superDistributer;
	}

	public void setRegistedTime(Date registedTime) {
		this.registedTime = registedTime;
	}

	public void setFollowedByStore(DepartmentYzw followedByStore) {
		this.followedByStore = followedByStore;
	}

	public void setDefaultCapitalAccount(CapitalAccount defaultCapitalAccount) {
		this.defaultCapitalAccount = defaultCapitalAccount;
	}

	public void setCustomer(CustomerYzw customer) {
		this.customer = customer;
	}

	public void setEmployee(EmployeeYzw employee) {
		this.employee = employee;
	}

	public void setSubordinates(List<Distributer> subordinates) {
		this.subordinates = subordinates;
	}

	public void setCapitalAccounts(Set<CapitalAccount> capitalAccounts) {
		this.capitalAccounts = capitalAccounts;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void setDistributerIncomes(List<DistributerIncome> distributerIncomes) {
		this.distributerIncomes = distributerIncomes;
	}

}
