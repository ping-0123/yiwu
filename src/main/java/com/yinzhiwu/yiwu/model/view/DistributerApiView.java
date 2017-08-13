package com.yinzhiwu.yiwu.model.view;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.entity.type.IncomeType;

public class DistributerApiView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1063578788280665376L;

	private int id;

	private String name;

	private String nickName;

	private String phoneNo;

	private String memeberId;

	private String shareCode;

	private String headIconUrl;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date birthDay;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date registerDate;

	private float exp;
	private int expGradeNo;
	
	private float neededExpForUpdate;

	private float brokerage;

	private float funds;

	private int customerId;

	@JsonIgnore
	private MultipartFile image;

	private float beatRate;

	public DistributerApiView() {
	}

	public DistributerApiView(int id) {
		this.id = id;
	}

	public DistributerApiView(Distributer d) {
		this.id = d.getId();
		this.name = d.getName();
		this.nickName = d.getNickName();
		this.phoneNo = d.getPhoneNo();
		this.memeberId = d.getMemberId();
		this.shareCode = d.getShareCode();
		this.birthDay = d.getBirthday();
		this.registerDate = d.getRegistedTime();
		this.brokerage = d.getIncomeValue(IncomeType.BROKERAGE);
		this.funds = d.getIncomeValue(IncomeType.FUNDS);
		
		DistributerIncome expIncome =d.getDistributerIncome(IncomeType.EXP);
		if(expIncome!= null){
			this.expGradeNo = expIncome.getIncomeGrade().getGradeNo();
			this.exp = expIncome.getIncome();
			this.neededExpForUpdate = expIncome.getIncomeGrade().getUpgradeNeededValue() - this.exp;
		}

		this.customerId = d.getCustomer().getId();
	}

	public DistributerApiView(Distributer d, Float rate) {
		this.id = d.getId();
		this.name = d.getName();
		this.nickName = d.getNickName();
		this.phoneNo = d.getPhoneNo();
		this.memeberId = d.getMemberId();
		this.shareCode = d.getShareCode();
		this.birthDay = d.getBirthday();
		this.registerDate = d.getRegistedTime();
		this.beatRate = rate;

		DistributerIncome brokerageIncome =d.getDistributerIncome(IncomeType.BROKERAGE);
		if(brokerageIncome== null)
			this.brokerage = 0f;
		else
			this.brokerage=brokerageIncome.getIncome();

		DistributerIncome fundsIncome =d.getDistributerIncome(IncomeType.FUNDS);
		if(fundsIncome== null)
			this.funds = 0f;
		else
			this.funds=fundsIncome.getIncome();
		
		DistributerIncome expIncome =d.getDistributerIncome(IncomeType.EXP);
		if(expIncome!= null){
			this.expGradeNo = expIncome.getIncomeGrade().getGradeNo();
			this.exp = expIncome.getIncome();
			this.neededExpForUpdate = expIncome.getIncomeGrade().getUpgradeNeededValue() - this.exp;
		}

		this.customerId = d.getCustomer().getId();
	}

	public float getBeatRate() {
		return beatRate;
	}

	public void setBeatRate(float beatRate) {
		this.beatRate = beatRate;
	}

	public int getId() {
		return id;
	}

	public int getExpGradeNo() {
		return expGradeNo;
	}

	public String getName() {
		return name;
	}

	public String getNickName() {
		return nickName;
	}

	public String getMemeberId() {
		return memeberId;
	}

	public String getShareCode() {
		return shareCode;
	}

	public String getHeadIconUrl() {
		return headIconUrl;
	}

	public float getNeededExpForUpdate() {
		return neededExpForUpdate;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setExpGradeNo(int expGradeNo) {
		this.expGradeNo = expGradeNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setMemeberId(String memeberId) {
		this.memeberId = memeberId;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}

	public void setNeededExpForUpdate(float neededExpForUpdate) {
		this.neededExpForUpdate = neededExpForUpdate;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public float getBrokerage() {
		return brokerage;
	}

	public float getFunds() {
		return funds;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public void setBrokerage(float brokerage) {
		this.brokerage = brokerage;
	}

	public void setFunds(float funds) {
		this.funds = funds;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public float getExp() {
		return exp;
	}

	public void setExp(float exp) {
		this.exp = exp;
	}

}
