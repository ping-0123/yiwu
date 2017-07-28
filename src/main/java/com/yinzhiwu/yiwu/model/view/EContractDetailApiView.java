package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.yzw.ElectricContractYzw;
import com.yinzhiwu.yiwu.enums.Gender;

public class EContractDetailApiView {

	private String contractNo;

	private String customerName;

	private Gender gender;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	private String identityCardNo;

	private String mobiePhoneNo;

	private String qqNo;

	private String wechatNo;

	private Integer customerId;

	private String contactAddress;

	private String homeAddress;

	private String recommendMemberCardNo;

	private String memberCardNo;

	private Date effectiveStart;

	private Date effectiveEnd;

	private int timesOfLesson;

	private float price;

	private float amount;

	private float promotionPrice;

	private String rangeOfApplication;

	private String supplementalInstruction;

	private String uppercaseAmount;

	// 小写金额
	private String lowercaseAmount;

	private Date payedDate;

	private String payedMethod;

	private Float depositAmount;

	private Date depositDate;

	private Float finalAmount;

	private Date finalDate;

	private String type;

	private String context;

	public EContractDetailApiView(ElectricContractYzw e) {
		Assert.notNull(e);
		this.contractNo = e.getContractNo();
		this.customerName = e.getCustomerName();
		this.gender = e.getGender();
		this.birthday = e.getBirthday();
		this.identityCardNo = e.getIdentityCardNo();
		this.mobiePhoneNo = e.getMobiePhoneNo();
		this.qqNo = e.getQqNo();
		this.wechatNo = e.getWechatNo();
		this.customerId = e.getCustomer().getId();
		this.contactAddress = e.getContactAddress();
		this.homeAddress = e.getHomeAddress();
		this.recommendMemberCardNo = e.getRecommendMemberCardNo();
		this.memberCardNo = e.getMemberCardNo();
		this.effectiveStart = e.getEffectiveStart();
		this.effectiveEnd = e.getEffectiveEnd();
		this.timesOfLesson = e.getTimesOfLesson();
		this.price = e.getPrice();
		this.amount = e.getAmount();
		this.promotionPrice = e.getPromotionPrice();
		this.rangeOfApplication = e.getRangeOfApplication();
		this.supplementalInstruction = e.getSupplementalInstruction();
		this.uppercaseAmount = e.getUppercaseAmount();
		this.lowercaseAmount = e.getLowercaseAmount();
		this.payedDate = e.getPayedDate();
		this.payedMethod = e.getPayedMethod();
		this.depositAmount = e.getDepositAmount();
		this.depositDate = e.getDepositDate();
		this.finalAmount = e.getFinalAmount();
		this.finalDate = e.getFinalDate();
		this.type = e.getContractType().getDescription();
		this.context = e.getContractType().getContent();
	}

	public String getCustomerName() {
		return customerName;
	}

	public Gender getGender() {
		return gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getIdentityCardNo() {
		return identityCardNo;
	}

	public String getMobiePhoneNo() {
		return mobiePhoneNo;
	}

	public String getQqNo() {
		return qqNo;
	}

	public String getWechatNo() {
		return wechatNo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public String getRecommendMemberCardNo() {
		return recommendMemberCardNo;
	}

	public String getMemberCardNo() {
		return memberCardNo;
	}

	public Date getEffectiveStart() {
		return effectiveStart;
	}

	public Date getEffectiveEnd() {
		return effectiveEnd;
	}

	public int getTimesOfLesson() {
		return timesOfLesson;
	}

	public float getPrice() {
		return price;
	}

	public float getAmount() {
		return amount;
	}

	public float getPromotionPrice() {
		return promotionPrice;
	}

	public String getRangeOfApplication() {
		return rangeOfApplication;
	}

	public String getSupplementalInstruction() {
		return supplementalInstruction;
	}

	public String getUppercaseAmount() {
		return uppercaseAmount;
	}

	public String getLowercaseAmount() {
		return lowercaseAmount;
	}

	public Date getPayedDate() {
		return payedDate;
	}

	public String getPayedMethod() {
		return payedMethod;
	}

	public Float getDepositAmount() {
		return depositAmount;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public String getType() {
		return type;
	}

	public String getContext() {
		return context;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setIdentityCardNo(String identityCardNo) {
		this.identityCardNo = identityCardNo;
	}

	public void setMobiePhoneNo(String mobiePhoneNo) {
		this.mobiePhoneNo = mobiePhoneNo;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}

	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public void setRecommendMemberCardNo(String recommendMemberCardNo) {
		this.recommendMemberCardNo = recommendMemberCardNo;
	}

	public void setMemberCardNo(String memberCardNo) {
		this.memberCardNo = memberCardNo;
	}

	public void setEffectiveStart(Date effectiveStart) {
		this.effectiveStart = effectiveStart;
	}

	public void setEffectiveEnd(Date effectiveEnd) {
		this.effectiveEnd = effectiveEnd;
	}

	public void setTimesOfLesson(int timesOfLesson) {
		this.timesOfLesson = timesOfLesson;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setPromotionPrice(float promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public void setRangeOfApplication(String rangeOfApplication) {
		this.rangeOfApplication = rangeOfApplication;
	}

	public void setSupplementalInstruction(String supplementalInstruction) {
		this.supplementalInstruction = supplementalInstruction;
	}

	public void setUppercaseAmount(String uppercaseAmount) {
		this.uppercaseAmount = uppercaseAmount;
	}

	public void setLowercaseAmount(String lowercaseAmount) {
		this.lowercaseAmount = lowercaseAmount;
	}

	public void setPayedDate(Date payedDate) {
		this.payedDate = payedDate;
	}

	public void setPayedMethod(String payedMethod) {
		this.payedMethod = payedMethod;
	}

	public void setDepositAmount(Float depositAmount) {
		this.depositAmount = depositAmount;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Float getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(Float finalAmount) {
		this.finalAmount = finalAmount;
	}

}
