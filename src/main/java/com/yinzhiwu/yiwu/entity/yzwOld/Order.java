package com.yinzhiwu.yiwu.entity.yzwOld;

import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vorder")
public class Order {

	@Id
	@Column(length = 32)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(length = 32)
	private String memberCardNo;

	@Column(name = "product_id")
	private Integer productId;

	@Column
	private Float markedPrice;

	@Column
	private Float preferential;

	@Column
	private Integer count;

	@Column
	private Float discount;

	@Column
	private Float payedAmount;

	@Column(name = "customer_id")
	private Integer customerId;

	// @DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "payed_date")
	private java.sql.Date payedDate;

	@Column(name = "course_id", length = 32)
	private String courseId;

	@Column(name = "checked_status", length = 32)
	private String checkedStatus;

	@Column(name = "store_id")
	private Integer storeId;

	@Column(length = 32, name = "vip_attr")
	private String vipAttr;

	@Column(length = 32)
	private String contractNo;

	@Column
	private Integer validity;

	@Column(name = "validity_times")
	private Integer validityTimes;

	@Column
	private Date startDate;

	@Column
	private Date endDate;

	@Column(name = "remain_times")
	private Float remainTimes;

	@Column(length = 32, name = "product_type")
	private String productType;

	@Column(length = 32, name = "product_subType")
	private String productSubType;

	@Column(length = 32)
	private String recommender;

	@Column(name = "valid_storeids")
	private String validStoreIds;

	@Column
	private String pic;

	@Column(name = "ask_for_leave_flag")
	private Integer askForLeaveFlag;

	@Column
	private String comments;

	@Column(length = 32, name = "relative_SD")
	private String relativeSD;

	@Column(length = 32, name = "check_privilege")
	private String checkPrivilege;

	@Column(length = 32, name = "current_status")
	private String currentStatus;

	@Column(name = "remain_hours")
	private float remainHours;

	@Column(name = "audit_flag")
	private Boolean isAuditedByFinance;

	@Column(name = "e_contract_text")
	private Blob eContractText;

	@Column(name = "e_contract_address")
	private String econtractAddress;

	@Column(name = "e_contract_picture_flag")
	private int eContractPictureFlag;

	@Column(name = "e_contract_status")
	private int eContractStatus;

	@Column(name = "sf_create_user")
	private Integer createUserId;

	@Column(name = "sf_last_change_user")
	private Integer lastChangeUserId;

	@Column(name = "sf_create_time")
	private Date createTime;

	@Column(name = "sf_last_change_time")
	private Date lastChangeTime;

	@Column
	private Integer machineCode;

	@Column(name = "sf_last_sync_TimeStamp")
	private Date lastSyncTimeStamp;

	@Column(name = "sf_last_change_timestamp")
	private Date lastChangeTimeStamp;

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getMemberCardNo() {
		return memberCardNo;
	}

	public final void setMemberCardNo(String memberCardNo) {
		this.memberCardNo = memberCardNo;
	}

	public final Integer getProductId() {
		return productId;
	}

	public final void setProductId(Integer productId) {
		this.productId = productId;
	}

	public final Float getMarkedPrice() {
		return markedPrice;
	}

	public final void setMarkedPrice(Float markedPrice) {
		this.markedPrice = markedPrice;
	}

	public final Float getPreferential() {
		return preferential;
	}

	public final void setPreferential(Float preferential) {
		this.preferential = preferential;
	}

	public final Integer getCount() {
		return count;
	}

	public final void setCount(Integer count) {
		this.count = count;
	}

	public final Float getDiscount() {
		return discount;
	}

	public final void setDiscount(Float discount) {
		this.discount = discount;
	}

	public final Float getPayedAmount() {
		return payedAmount;
	}

	public final void setPayedAmount(Float payedAmount) {
		this.payedAmount = payedAmount;
	}

	public final Integer getCustomerId() {
		return customerId;
	}

	public final void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public final java.sql.Date getPayedDate() {
		return payedDate;
	}

	public final void setPayedDate(java.sql.Date payedDate) {
		this.payedDate = payedDate;
	}

	public final String getCourseId() {
		return courseId;
	}

	public final void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public final String getCheckedStatus() {
		return checkedStatus;
	}

	public final void setCheckedStatus(String checkedStatus) {
		this.checkedStatus = checkedStatus;
	}

	public final Integer getStoreId() {
		return storeId;
	}

	public final void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public final String getVipAttr() {
		return vipAttr;
	}

	public final void setVipAttr(String vipAttr) {
		this.vipAttr = vipAttr;
	}

	public final String getContractNo() {
		return contractNo;
	}

	public final void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public final Integer getValidity() {
		return validity;
	}

	public final void setValidity(Integer vilidity) {
		this.validity = vilidity;
	}

	public final Integer getValidityTimes() {
		return validityTimes;
	}

	public final void setValidityTimes(Integer validityTimes) {
		this.validityTimes = validityTimes;
	}

	public final Date getStartDate() {
		return startDate;
	}

	public final void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public final Date getEndDate() {
		return endDate;
	}

	public final void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public final Float getRemainTimes() {
		return remainTimes;
	}

	public final void setRemainTimes(Float remainTimes) {
		this.remainTimes = remainTimes;
	}

	public final String getProductType() {
		return productType;
	}

	public final void setProductType(String productType) {
		this.productType = productType;
	}

	public final String getProductSubType() {
		return productSubType;
	}

	public final void setProductSubType(String productSubType) {
		this.productSubType = productSubType;
	}

	public final String getRecommender() {
		return recommender;
	}

	public final void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public final String getValidStoreIds() {
		return validStoreIds;
	}

	public final void setValidStoreIds(String validStoreIds) {
		this.validStoreIds = validStoreIds;
	}

	public final String getPic() {
		return pic;
	}

	public final void setPic(String pic) {
		this.pic = pic;
	}

	public final Integer getAskForLeaveFlag() {
		return askForLeaveFlag;
	}

	public final void setAskForLeaveFlag(Integer askForLeaveFlag) {
		this.askForLeaveFlag = askForLeaveFlag;
	}

	public final String getComments() {
		return comments;
	}

	public final void setComments(String comments) {
		this.comments = comments;
	}

	public final String getRelativeSD() {
		return relativeSD;
	}

	public final void setRelativeSD(String relativeSD) {
		this.relativeSD = relativeSD;
	}

	public final String getCheckPrivilege() {
		return checkPrivilege;
	}

	public final void setCheckPrivilege(String checkPrivilege) {
		this.checkPrivilege = checkPrivilege;
	}

	public final String getCurrentStatus() {
		return currentStatus;
	}

	public final void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public final Integer getCreateUserId() {
		return createUserId;
	}

	public final void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public final Integer getLastChangeUserId() {
		return lastChangeUserId;
	}

	public final void setLastChangeUserId(Integer lastChangeUserId) {
		this.lastChangeUserId = lastChangeUserId;
	}

	public final Date getCreateTime() {
		return createTime;
	}

	public final void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public final Date getLastChangeTime() {
		return lastChangeTime;
	}

	public final void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	public final Integer getMachineCode() {
		return machineCode;
	}

	public final void setMachineCode(Integer machineCode) {
		this.machineCode = machineCode;
	}

	public final Date getLastSyncTimeStamp() {
		return lastSyncTimeStamp;
	}

	public final void setLastSyncTimeStamp(Date lastSyncTimeStamp) {
		this.lastSyncTimeStamp = lastSyncTimeStamp;
	}

	public final Date getLastChangeTimeStamp() {
		return lastChangeTimeStamp;
	}

	public final void setLastChangeTimeStamp(Date lastChangeTimeStamp) {
		this.lastChangeTimeStamp = lastChangeTimeStamp;
	}

	public float getRemainHours() {
		return remainHours;
	}

	public Boolean getIsAuditedByFinance() {
		return isAuditedByFinance;
	}

	public Blob geteContractText() {
		return eContractText;
	}

	public int geteContractPictureFlag() {
		return eContractPictureFlag;
	}

	public int geteContractStatus() {
		return eContractStatus;
	}

	public void setRemainHours(float remainHours) {
		this.remainHours = remainHours;
	}

	public void setIsAuditedByFinance(Boolean isAuditedByFinance) {
		this.isAuditedByFinance = isAuditedByFinance;
	}

	public void seteContractText(Blob eContractText) {
		this.eContractText = eContractText;
	}

	public void seteContractPictureFlag(int eContractPictureFlag) {
		this.eContractPictureFlag = eContractPictureFlag;
	}

	public void seteContractStatus(int eContractStatus) {
		this.eContractStatus = eContractStatus;
	}

}
