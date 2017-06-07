package com.yinzhiwu.springmvc3.entity;

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

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.DepartmentYzw;
import com.yinzhiwu.springmvc3.enums.Gender;
import com.yinzhiwu.springmvc3.model.DistributerRegisterModel;

/**
 * 
 * @author ping
 *
 */
@Entity
/**
 * 外键命名规则:  fk_{table name}_{foreign key name}
 * 外键列命名规则: {parent table name}_{referenced column name}
 * 唯一键命名规则: uk_{table name}_{unique key name}
 */
@Table(name="yiwu_distributer", 
	uniqueConstraints={
			@UniqueConstraint(name="uk_distributer_memberId", columnNames={"memberId"}),
			@UniqueConstraint(name="uk_distributer_account", columnNames={"account"}),
			@UniqueConstraint(name="uk_distributer_wechatNo", columnNames="wechatNO"),
			@UniqueConstraint(name="uk_distributer_phoneNo", columnNames="phoneNo"),
			@UniqueConstraint(name="uk_distributer_shareCode", columnNames="shareCode"),
//			@UniqueConstraint(name="uk_distributer_headIconName", columnNames="headIconName"),
			@UniqueConstraint(name="fuk_distributer_customer_id", columnNames="customer_id"),
			@UniqueConstraint(name="fuk_distributer_defaultCapitalAccount_id", columnNames="defaultCapitalAccount_id")})
public class Distributer extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8400038437062433347L;
//	unique=true,
	@Column(length=32, nullable=false, updatable=false)
//	@Formula("concat('E5', lpad(id,8,'0'))")
//	@ColumnDefault("concat('E5', lpad(id,8,'0'))")
	private String memberId;
	
	

	@Column(length=32)
	@Size(min=2, max=32)
	private String name;
	
	@Size(min=2,max=32)
	@Column(length=32)  
	private String nickName; //默认是会员Id
	
//	@Pattern(regexp="(\\p{Alpha}|\\d|_){5,31}")
//	@NotNull
//	unique
	@Column(length=32, nullable=false, updatable=false)
	private String account;  //默认是手机号
	
	@Column(length=32, nullable=false)
	private String password;
	
//	unique
	@NotNull
	@Column(length=32,  nullable=false)
	private String wechatNo;
	
//	unique
	@Pattern(regexp="^1\\d{10}$")
	@Column(length=32, nullable=false)
	private String phoneNo;
	
//	@Past
	@Column
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
//	@NotNull
	@Column(length=10)
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;
	
//	unique
	@Column(length=10, updatable=false)
	private String shareCode;
	
	@ManyToOne(targetEntity=Distributer.class, fetch=FetchType.LAZY)
	@JoinColumn(name="super_distributer_id",
		foreignKey=@ForeignKey(name="fk_distributer_superDistributer_id"))
	private Distributer superDistributer;
	
//	unique
	@Column(length=255, name="headIconUrl")
	private String headIconName;
	
	private Float exp;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="expGrade_id", referencedColumnName="id", 
//		unique=true,
		foreignKey=@ForeignKey(name="fk_distributer_expGrade_id"))
	private ExpGrade expGrade;
	
	private Float brokerage;
	
	private Float funds;
	
	private Float accumulativeBrokerage;
	
	private Float accumulativeFunds;
	

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date registedTime;
	
//	unique
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id", foreignKey=@ForeignKey(
			name="fk_distributer_customer_id", value = ConstraintMode.NO_CONSTRAINT))
	private CustomerYzw customer;  //根据手机号码 或者微信号做唯一性关联
	

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="followedByStore_id", foreignKey=
		@ForeignKey(name="fk_distributer_followedByStore_id", value=ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw followedByStore;
	
//	unique
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_distributer_defaultCapitalAccount_id"))
	private CapitalAccount defaultCapitalAccount;
	
	@JsonIgnore
	@OneToMany(mappedBy="superDistributer")
	private List<Distributer> subordinates = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="distributer")
	private Set<CapitalAccount> capitalAccounts = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="beneficiaty", targetEntity=MoneyRecord.class)
	private List<MoneyRecord> moneyRecords = new ArrayList<>();
	
	@OneToMany(mappedBy="contributor", targetEntity=MoneyRecord.class)
	private List<MoneyRecord> contributedMoneyRecords  = new ArrayList<>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy="beneficiaty", targetEntity=ExpRecord.class)
	private List<ExpRecord> expRecords = new ArrayList<>();
	
	@OneToMany(mappedBy="contributor", targetEntity=ExpRecord.class)
	private List<ExpRecord> contributedExpRecords = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="receiver")
	private List<Message> messages = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="sharer")
	private List<ShareTweet> shareTweets = new ArrayList<>();
	
	@Override
	public void init() {
		super.init();
		if(!StringUtils.hasLength(this.getName()))
			setName(getPhoneNo());
		if(!StringUtils.hasLength(getAccount()))
			setAccount(getPhoneNo());
		if(!StringUtils.hasLength(getNickName()))
			setNickName(getPhoneNo());
		if(!StringUtils.hasLength(getPassword()))
			this.password = "yzw123456";
		this.registedTime = super.getCreateDate();
		this.exp = 0f;
		this.funds = 0f;
		this.brokerage =0f;
		this.accumulativeBrokerage = 0f;
		this.accumulativeFunds = 0f;
	}

	
	public Distributer() {
	}
	
	public void initialize(){
		if (StringUtils.isEmpty(this.account)){
			this.account = this.phoneNo;
		}
	}
	
	public Distributer(DistributerRegisterModel m){
		super();
		this.registedTime = getCreateDate();
		this.phoneNo = m.getPhoneNo();
		this.account = m.getAccount();
		this.password = m.getPassword();
		this.name = m.getName();
		this.nickName = m.getNickName();
		this.gender = m.getGender();
		this.birthday =m.getBirthDay();
		DepartmentYzw d = new DepartmentYzw();
		d.setId(m.getFollowedByStoreId());
		this.setFollowedByStore(d);
		this.wechatNo = m.getWechatNo();
	}
	
	public int getAge(){
		Calendar today = Calendar.getInstance();
		Calendar bir = Calendar.getInstance();
		bir.setTime(birthday);
		long age = (today.getTimeInMillis() - bir.getTimeInMillis())/(1000*3600*24*365);
		return (int) age;
	}
	
	public boolean isAudit(){
		if(getAge()>=18)
			return true;
		else
			return false;
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

	public float getExp() {
		return exp;
	}

	public void setExp(float exp) {
		this.exp = exp;
	}


	public ExpGrade getExpGrade() {
		return expGrade;
	}

	public void setExpGrade(ExpGrade expGrade) {
		this.expGrade = expGrade;
	}


	public float getFunds() {
		return funds;
	}


	public Date getRegistedTime() {
		return registedTime;
	}


	public Set<CapitalAccount> getCapitalAccounts() {
		return capitalAccounts;
	}


	public void setFunds(float funds) {
		this.funds = funds;
	}

	public void setRegistedTime(Date registedTime) {
		this.registedTime = registedTime;
	}

	public void setCapitalAccounts(Set<CapitalAccount> capitalAccounts) {
		this.capitalAccounts = capitalAccounts;
	}



	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	

	public List<ShareTweet> getShareTweets() {
		return shareTweets;
	}

	public void setShareTweets(List<ShareTweet> shareTweets) {
		this.shareTweets = shareTweets;
	}



	public List<MoneyRecord> getMoneyRecords() {
		return moneyRecords;
	}


	public List<ExpRecord> getExpRecords() {
		return expRecords;
	}

	public void setMoneyRecords(List<MoneyRecord> moneyRecords) {
		this.moneyRecords = moneyRecords;
	}

	public void setExpRecords(List<ExpRecord> expRecords) {
		this.expRecords = expRecords;
	}


	public CustomerYzw getCustomer() {
		return customer;
	}


	public void setCustomer(CustomerYzw customer) {
		this.customer = customer;
	}


	public String getName() {
		return name;
	}


	public String getNickName() {
		return nickName;
	}


	public String getAccount() {
		return account;
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


	public Distributer getSuperDistributer() {
		return superDistributer;
	}


	public List<Distributer> getSubordinates() {
		return subordinates;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public void setAccount(String account) {
		this.account = account;
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


	public void setSuperDistributer(Distributer superDistributer) {
		this.superDistributer = superDistributer;
	}


	public void setSubordinates(List<Distributer> subordinates) {
		this.subordinates = subordinates;
	}


	public DepartmentYzw getFollowedByStore() {
		return followedByStore;
	}


	public void setFollowedByStore(DepartmentYzw followedByStore) {
		this.followedByStore = followedByStore;
	}


	public float getBrokerage() {
		return brokerage;
	}


	public void setBrokerage(float brokerage) {
		this.brokerage = brokerage;
	}


	

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getAccumulativeBrokerage() {
		return accumulativeBrokerage;
	}

	public float getAccumulativeFunds() {
		return accumulativeFunds;
	}

	public void setAccumulativeBrokerage(float accumulativeBrokerage) {
		this.accumulativeBrokerage = accumulativeBrokerage;
	}

	public void setAccumulativeFunds(float accumulativeFunds) {
		this.accumulativeFunds = accumulativeFunds;
	}

	public CapitalAccount getDefaultCapitalAccount() {
		return defaultCapitalAccount;
	}

	public void setDefaultCapitalAccount(CapitalAccount defaultCapitalAccount) {
		this.defaultCapitalAccount = defaultCapitalAccount;
	}

	@Override
	public boolean equals(Object d) {
		if(this == d)
			return true;
		if(d instanceof Distributer)
			return this.getId()!=null && this.getId() == ((Distributer) d).getId();
		return false;
	}

	public List<MoneyRecord> getContributedMoneyRecords() {
		return contributedMoneyRecords;
	}

	public List<ExpRecord> getContributedExpRecords() {
		return contributedExpRecords;
	}

	public void setContributedMoneyRecords(List<MoneyRecord> contributedMoneyRecords) {
		this.contributedMoneyRecords = contributedMoneyRecords;
	}

	public void setContributedExpRecords(List<ExpRecord> contributedExpRecords) {
		this.contributedExpRecords = contributedExpRecords;
	}

	
	
}
