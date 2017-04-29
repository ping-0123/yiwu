package com.yinzhiwu.springmvc3.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Distributer extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8400038437062433347L;
	
	@Column(length=32,unique=true, nullable=false, updatable=false)
	private String memberId;
	
	@Column(length=10, unique=true, updatable=false)
	private String shareCode;
	
	@ManyToOne(targetEntity=Distributer.class)
	@JoinColumn(name="super_proxy_id")
	private Distributer superProxy;
	
	@Column(length=32)
	private String headIconName;
	
	private float exp;
	
	@ManyToOne
	@JoinColumn(name="exp_grade_id", referencedColumnName="id")
	private ExpGrade expGrade;
	
	private float money;
	
	private float funds;
	
	private Date registedTime;
	
	@OneToOne
	@JoinColumn(name="id")
	private Customer customer;
	
	@OneToMany(mappedBy="superProxy")
	private List<Distributer> subordinates = new ArrayList<>();
	
	@OneToMany(mappedBy="distributer")
	private Set<CapitalAccount> capitalAccounts = new HashSet<>();
	
	@OneToMany(mappedBy="beneficiaty", targetEntity=MoneyRecord.class)
	private List<MoneyRecord> moneyRecords = new ArrayList<>();
	
	@OneToMany(mappedBy="beneficiaty", targetEntity=ExpRecord.class)
	private List<ExpRecord> expRecords = new ArrayList<>();
	
	@OneToMany(mappedBy="receiver")
	private List<Message> messages = new ArrayList<>();
	
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


	public Distributer getSuperProxy() {
		return superProxy;
	}

	public void setSuperProxy(Distributer superProxy) {
		this.superProxy = superProxy;
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


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	
	
}
