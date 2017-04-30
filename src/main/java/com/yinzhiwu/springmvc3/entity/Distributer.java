package com.yinzhiwu.springmvc3.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="distributer", 
	uniqueConstraints={@UniqueConstraint(name="uk_distributer_memberId", columnNames={"memberId"}),
			@UniqueConstraint(name="uk_distributer_account", columnNames={"account"}),
			@UniqueConstraint(name="uk_distributer_wechatNo", columnNames="wechatNO"),
			@UniqueConstraint(name="uk_distributer_phoneNo", columnNames="phoneNo"),
			@UniqueConstraint(name="uk_distributer_shareCode", columnNames="shareCode"),
			@UniqueConstraint(name="uk_distributer_headIconName", columnNames="headIconName"),
			@UniqueConstraint(name="fuk_distributer_customer_id", columnNames="customer_id")})
public class Distributer extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8400038437062433347L;
	
	@Column(length=32,unique=true, nullable=false, updatable=false)
//	@Formula("concat('E5', lpad(id,8,'0'))")
//	@ColumnDefault("concat('E5', lpad(id,8,'0'))")
	private String memberId;
	
	
	@Size(min=2, max=32)
	@Column(length=32)
	private String name;
	
	@Size(min=2,max=32)
	@Column(length=32)  
	private String nickName; //默认是会员Id
	
	@Pattern(regexp="(\\p{Alpha}|\\d|_){5,31}")
	@NotNull
	@Column(length=32, unique=true, nullable=false, updatable=false)
	private String account;  //默认是手机号
	
	@Column(length=32, nullable=false)
	private String passwords;
	
	@Column(length=32, unique=true, nullable=false)
	private String wechatNo;
	
	@Pattern(regexp="^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$")
	@Column(length=32, unique=true, nullable=false)
	private String phoneNo;
	
	@Column
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	@Column(length=10, unique=true, updatable=false)
	private String shareCode;
	
	@JsonIgnore
	@ManyToOne(targetEntity=Distributer.class)
	@JoinColumn(name="super_distributer_id", foreignKey=@ForeignKey(name="fk_distributer_superDistributer_id"))
	private Distributer superDistributer;
	
	@Column(length=32, unique=true)
	private String headIconName;
	
	private float exp;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="expGrade_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_distributer_expGrade_id"))
	private ExpGrade expGrade;
	
	private float money;
	
	private float funds;
	

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date registedTime;
	
	@OneToOne
	@JoinColumn(name="customer_id", unique=true, foreignKey=@ForeignKey(name="fk_distributer_customer_id"))
	private Customer customer;  //根据手机号码 或者微信号做唯一性关联
	

	@ManyToOne
	@JoinColumn(name="followedByStore_id", foreignKey=@ForeignKey(name="fk_distributer_followedByStore_id"))
	private Department followedByStore;
	
	@JsonIgnore
	@OneToMany(mappedBy="superDistributer")
	private List<Distributer> subordinates = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="distributer")
	private Set<CapitalAccount> capitalAccounts = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="beneficiaty", targetEntity=MoneyRecord.class)
	private List<MoneyRecord> moneyRecords = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="beneficiaty", targetEntity=ExpRecord.class)
	private List<ExpRecord> expRecords = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="receiver")
	private List<Message> messages = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="sharer")
	private List<ShareTweet> shareTweets = new ArrayList<>();


	
	public Distributer() {
		super();
		this.registedTime = getCreateDate();
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

	public float getMoney() {
		return money;
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



	public void setMoney(float money) {
		this.money = money;
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


//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
//	

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


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
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


	public String getPasswords() {
		return passwords;
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


	public void setPasswords(String passwords) {
		this.passwords = passwords;
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

	
}
