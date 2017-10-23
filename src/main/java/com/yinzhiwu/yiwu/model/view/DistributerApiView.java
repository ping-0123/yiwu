package com.yinzhiwu.yiwu.model.view;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Distributer.Role;
import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

@SuppressWarnings("serial")
@MapedClass(Distributer.class)
public class DistributerApiView  implements Serializable {

	/**
	 * 
	 */

	private int id;
	private String name;
	private String nickName;
	private String phoneNo;
	private String memberCard;
	private String shareCode;
	@MapedProperty("headIconName")
	private String headIconUrl;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date birthday;
	
	@MapedProperty("registedTime")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date registerDate;
	
	@MapedProperty(ignored=true)
	private float exp;
	
	@MapedProperty(ignored=true)
	private int expGradeNo;
	
	@MapedProperty(ignored=true)
	private float neededExpForUpdate;
	
	@MapedProperty(ignored=true)
	private float brokerage;
	
	@MapedProperty(ignored=true)
	private float funds;
	
	@MapedProperty("customer.id")
	private int customerId;
	
	@MapedProperty("employee.id")
	private int empoyeeId;
	
	@MapedProperty(ignored=true)
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
		this.memberCard = d.getMemberCard();
		this.shareCode = d.getShareCode();
		this.birthday = d.getBirthday();
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
		if(Role.EMPLOYEE == d.getRole())
			this.empoyeeId = d.getEmployee().getId();
	}

	public DistributerApiView(Distributer d, Float rate) {
		this.id = d.getId();
		this.name = d.getName();
		this.nickName = d.getNickName();
		this.phoneNo = d.getPhoneNo();
		this.memberCard = d.getMemberCard();
		this.shareCode = d.getShareCode();
		this.birthday = d.getBirthday();
		this.registerDate = d.getRegistedTime();
		this.beatRate = rate;
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


	public String getShareCode() {
		return shareCode;
	}

	public String getHeadIconUrl() {
		return headIconUrl;
	}

	public float getNeededExpForUpdate() {
		return neededExpForUpdate;
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


	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}

	public void setNeededExpForUpdate(float neededExpForUpdate) {
		this.neededExpForUpdate = neededExpForUpdate;
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


	public float getExp() {
		return exp;
	}

	public void setExp(float exp) {
		this.exp = exp;
	}

	public int getEmpoyeeId() {
		return empoyeeId;
	}

	public void setEmpoyeeId(int empoyeeId) {
		this.empoyeeId = empoyeeId;
	}

	public DistributerApiView(int id, String name, String nickName, String phoneNo, String memeberId, String shareCode,
			String headIconUrl, Date birthDay, Date registerDate, float exp, int expGradeNo, float neededExpForUpdate,
			float brokerage, float funds, int customerId, int empoyeeId, float beatRate) {
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.phoneNo = phoneNo;
		this.memberCard = memeberId;
		this.shareCode = shareCode;
		this.headIconUrl = headIconUrl;
		this.birthday = birthDay;
		this.registerDate = registerDate;
		this.exp = exp;
		this.expGradeNo = expGradeNo;
		this.neededExpForUpdate = neededExpForUpdate;
		this.brokerage = brokerage;
		this.funds = funds;
		this.customerId = customerId;
		this.empoyeeId = empoyeeId;
		this.beatRate = beatRate;
	}

	public String getMemberCard() {
		return memberCard;
	}

	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	public final static class DistributerApiViewConverter extends AbstractConverter<Distributer, DistributerApiView>{
		public final static DistributerApiViewConverter instance = new DistributerApiViewConverter(); 
	}
}
