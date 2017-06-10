package com.yinzhiwu.springmvc3.model.view;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.util.UrlUtil;

public class DistributerApiView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1063578788280665376L;
	
	private static final Log LOG= LogFactory.getLog(DistributerApiView.class);

	@Min(1)
	private int id;
	
	private int expGradeNo;
	
	private String name;
	
	private String nickName;
	
	private String phoneNo;
	
	private String memeberId;
	
	private String shareCode;
	
	private String headIconUrl;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date registerDate;
	
	private float neededExpForUpdate;
	
	private float brokerage;
	
	private float funds;
	
	private int customerId;
	
	@NotNull
	@JsonIgnore
	private MultipartFile image;
	
	
	private float beatRate;

	public DistributerApiView() {
	}
	
	public DistributerApiView(int id) {
		this.id = id;
	}
	
	
	public DistributerApiView(Distributer d){
		this.id = d.getId();
		this.expGradeNo = d.getExpGrade().getGradeNo();
		this.name = d.getName();
		this.nickName = d.getNickName();
		this.phoneNo = d.getPhoneNo();
		this.memeberId = d.getMemberId();
		this.shareCode = d.getShareCode();
		this.headIconUrl = UrlUtil.toHeadIcomUrl(d.getHeadIconName());
		this.registerDate = d.getRegistedTime();
		this.neededExpForUpdate = d.getExpGrade().getUpgradeExp()-d.getExp();
		this.brokerage = d.getBrokerage();
		this.funds = d.getFunds();
		CustomerYzw customer = d.getCustomer();
		if(customer != null)
			this.customerId = customer.getId();
	}
	
	public DistributerApiView(Distributer d, Float rate){
		try{
			this.id = d.getId();
//			this.expGradeNo = d.getExpGrade().getGradeNo();
			this.name = d.getName();
			this.nickName = d.getNickName();
			this.phoneNo = d.getPhoneNo();
			this.memeberId = d.getMemberId();
			this.shareCode = d.getShareCode();
			this.headIconUrl = UrlUtil.toHeadIcomUrl(d.getHeadIconName());
			this.registerDate = d.getRegistedTime();
			this.beatRate = rate;
			this.brokerage = d.getDistributerIncome(IncomeType.BROKERAGE).getIncome();
			this.funds = d.getDistributerIncome(IncomeType.FUNDS).getIncome();
			this.expGradeNo = d.getDistributerIncome(IncomeType.EXP).getIncomeGrade().getGradeNo();
			this.neededExpForUpdate = d.getDistributerIncome(IncomeType.EXP).getIncomeGrade().getUpgradeNeededValue()
					-d.getDistributerIncome(IncomeType.EXP).getIncome();
			this.customerId = d.getCustomer().getId();
		}catch (Exception e) {
			LOG.error(e);
		}
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
	
	
}
