package com.yinzhiwu.springmvc3.entity.yzw;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="velectric_contract")
public class ElectricContractYzw {

	@Id
	@GeneratedValue(generator="assigned")  
	@GenericGenerator(name="assigned", strategy = "assigned")
	private String contractNo;
	
	private String customerName;
	
	private String gender;
	
	private Date birthday;
	
	@Column(name="IdentityCardNo")
	private String identityCardNo;
	
	private String mobiePhoneNo;
	
	private String qqNo;
	
	private String wechatNo;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name="customerId", foreignKey=
			@ForeignKey(name="fk_electricContract_customer_id", 
						value=ConstraintMode.NO_CONSTRAINT))
	private CustomerYzw customer; 
	
	private String contactAddress;
	
	private String homeAddress;
	
	private String recommendMemberCardNo;
	
	private String memberCardNo;
	
	private Date effectiveStart;
	
	private Date effectiveEnd;
	
	private Integer timesOfLesson;
	
	private Float price;
	
	private Float amount;
	
	private Float promotionPrice;
	
	private String rangeOfApplication;
	
	private String supplementalInstruction;
	
	//大写金额
	@Column(name="uppcaseAmount")
	private String uppercaseAmount;
	
	//小写金额
	private String lowercaseAmount;
	
	private Date payedDate;
	
	private String payedMethod;
	
	private Float depositAmount;
	
	private Date depositDate;
	
	@Column(name="finalPayment")
	private Float finalAmount;
	
	private Date finalDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contractType", foreignKey=
			@ForeignKey(name="fk_ElectricContract_contractType",
					value=ConstraintMode.NO_CONSTRAINT))
	private ElectricContractTypeYzw contractType;
	
	private Boolean isConfirmed;
	
	@JsonIgnore
	private Integer sf_create_user;
	
	@JsonIgnore
	private Integer sf_last_change_user;
	
	@JsonIgnore
	private Date sf_create_time;
	
	@JsonIgnore
	private Date sf_last_change_time;
	
	public ElectricContractYzw(){
	}



	public void init() {
		this.sf_last_change_user =1;
		this.sf_create_user = 1;
		Date date = new Date();
		this.sf_create_time = date;
		this.sf_last_change_time = date;
	}



	public String getContractNo() {
		return contractNo;
	}



	public String getCustomerName() {
		return customerName;
	}



	public String getGender() {
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



	public CustomerYzw getCustomer() {
		return customer;
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



	public Integer getTimesOfLesson() {
		return timesOfLesson;
	}



	public Float getPrice() {
		return price;
	}



	public Float getAmount() {
		return amount;
	}



	public Float getPromotionPrice() {
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



	public ElectricContractTypeYzw getContractType() {
		return contractType;
	}



	public Boolean getIsConfirmed() {
		return isConfirmed;
	}



	public Integer getSf_create_user() {
		return sf_create_user;
	}



	public Integer getSf_last_change_user() {
		return sf_last_change_user;
	}



	public Date getSf_create_time() {
		return sf_create_time;
	}



	public Date getSf_last_change_time() {
		return sf_last_change_time;
	}



	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public void setGender(String gender) {
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



	public void setCustomer(CustomerYzw customer) {
		this.customer = customer;
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



	public void setTimesOfLesson(Integer timesOfLesson) {
		this.timesOfLesson = timesOfLesson;
	}



	public void setPrice(Float price) {
		this.price = price;
	}



	public void setAmount(Float amount) {
		this.amount = amount;
	}



	public void setPromotionPrice(Float promotionPrice) {
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



	public void setContractType(ElectricContractTypeYzw contractType) {
		this.contractType = contractType;
	}



	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}



	public void setSf_create_user(Integer sf_create_user) {
		this.sf_create_user = sf_create_user;
	}



	public void setSf_last_change_user(Integer sf_last_change_user) {
		this.sf_last_change_user = sf_last_change_user;
	}



	public void setSf_create_time(Date sf_create_time) {
		this.sf_create_time = sf_create_time;
	}



	public void setSf_last_change_time(Date sf_last_change_time) {
		this.sf_last_change_time = sf_last_change_time;
	}



	public Float getFinalAmount() {
		return finalAmount;
	}



	public void setFinalAmount(Float finalAmount) {
		this.finalAmount = finalAmount;
	}
	


	
	
	
}
