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
import javax.persistence.Table;

import org.springframework.test.annotation.Commit;

@Entity
@Table
public class Distributer extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8400038437062433347L;
	
	
	private String memberId;
	
	private String shareCode;
	
	
	private Distributer superProxy;
	
	private String headIconName;
	
	private float exp;
	
	
	private ExpGrade expGrade;
	
	private float money;
	
	private float funds;
	
	private Date registedTime;
	
	private Set<CapitalAccount> capitalAccounts = new HashSet<>();
	
	private List<AbstractRecord> records = new ArrayList<>();
	
	private List<Message> messages = new ArrayList<>();

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

	@ManyToOne(targetEntity=Distributer.class)
	@JoinColumn(name="super_proxy_id")
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

	@ManyToOne
	@JoinColumn(name="exp_grade_id", referencedColumnName="id")
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

	@OneToMany(mappedBy="distributer")
	public Set<CapitalAccount> getCapitalAccounts() {
		return capitalAccounts;
	}

	@OneToMany(mappedBy="beneficiaty")
	public List<AbstractRecord> getRecords() {
		return records;
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

	public void setRecords(List<AbstractRecord> records) {
		this.records = records;
	}

	@OneToMany(mappedBy="receiver")
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	
	
}
