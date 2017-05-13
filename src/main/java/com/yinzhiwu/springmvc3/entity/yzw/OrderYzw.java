package com.yinzhiwu.springmvc3.entity.yzw;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="vorder")
public class OrderYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8473575190806095432L;

	@Id
	@Column(length=32)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	@Column(length=32)
	private String memberCardNo;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="product_id", foreignKey=
			@ForeignKey(name="fk_order_product_id", value=ConstraintMode.NO_CONSTRAINT))
	private ProductYzw product;
	
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
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id", foreignKey=
			@ForeignKey(name="fk_order_customer_id", value=ConstraintMode.NO_CONSTRAINT))
	private CustomerYzw customer;
	
	@Column(name="payed_date")
	private Date payedDate;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="course_id", foreignKey=
			@ForeignKey(name="fk_order_course_id", value=ConstraintMode.NO_CONSTRAINT))
	private CourseYzw course;
	
	@Column(name="checked_status", length=32)
	private String checkedStatus;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="store_id", foreignKey=
			@ForeignKey(name="fk_order_store_id", value=ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw store;
	
	@Column(length=32, name="vip_attr")
	private String vipAttr;
	
	@Column(length=32)
	private String contractNo;
	
	@Column
	private Integer validity;
	
	@Column(name="validity_times")
	private Integer validityTimes;
	
	@Column
	private Date startDate;
	
	@Column
	private Date endDate;
	
	@Column(name="remain_times")
	private Float remainTimes;
	
	@Column(length=32, name="product_type")
	private String productType;
	
	@Column(length=32, name="product_subType")
	private String productSubType;
	
	@Column(length=32)
	private String recommender;
	
	@Column(name="valid_storeids")
	private String validStoreIds;
	
	@Column
	private String pic;
	
	@Column(name="ask_for_leave_flag")
	private Integer askForLeaveFlag;
	
	@Column
	private String comments;
	
	@Column(length=32, name="relative_SD")
	private String relativeSD;
	
	@Column(length=32 ,name="check_privilege")
	private String checkPrivilege;
	
	@Column(length=32, name="current_status")
	private String currentStatus;
	
	@Column(length=32)
	private String subType;

	public OrderYzw() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getMemberCardNo() {
		return memberCardNo;
	}

	public ProductYzw getProduct() {
		return product;
	}

	public Float getMarkedPrice() {
		return markedPrice;
	}

	public Float getPreferential() {
		return preferential;
	}

	public Integer getCount() {
		return count;
	}

	public Float getDiscount() {
		return discount;
	}

	public Float getPayedAmount() {
		return payedAmount;
	}

	public CustomerYzw getCustomer() {
		return customer;
	}

	public Date getPayedDate() {
		return payedDate;
	}

	public CourseYzw getCourse() {
		return course;
	}

	public String getCheckedStatus() {
		return checkedStatus;
	}

	public DepartmentYzw getStore() {
		return store;
	}

	public String getVipAttr() {
		return vipAttr;
	}

	public String getContractNo() {
		return contractNo;
	}

	public Integer getValidity() {
		return validity;
	}

	public Integer getValidityTimes() {
		return validityTimes;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Float getRemainTimes() {
		return remainTimes;
	}

	public String getProductType() {
		return productType;
	}

	public String getProductSubType() {
		return productSubType;
	}

	public String getRecommender() {
		return recommender;
	}

	public String getValidStoreIds() {
		return validStoreIds;
	}

	public String getPic() {
		return pic;
	}

	public Integer getAskForLeaveFlag() {
		return askForLeaveFlag;
	}

	public String getComments() {
		return comments;
	}

	public String getRelativeSD() {
		return relativeSD;
	}

	public String getCheckPrivilege() {
		return checkPrivilege;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public String getSubType() {
		return subType;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMemberCardNo(String memberCardNo) {
		this.memberCardNo = memberCardNo;
	}

	public void setProduct(ProductYzw product) {
		this.product = product;
	}

	public void setMarkedPrice(Float markedPrice) {
		this.markedPrice = markedPrice;
	}

	public void setPreferential(Float preferential) {
		this.preferential = preferential;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public void setPayedAmount(Float payedAmount) {
		this.payedAmount = payedAmount;
	}

	public void setCustomer(CustomerYzw customer) {
		this.customer = customer;
	}

	public void setPayedDate(Date payedDate) {
		this.payedDate = payedDate;
	}

	public void setCourse(CourseYzw course) {
		this.course = course;
	}

	public void setCheckedStatus(String checkedStatus) {
		this.checkedStatus = checkedStatus;
	}

	public void setStore(DepartmentYzw store) {
		this.store = store;
	}

	public void setVipAttr(String vipAttr) {
		this.vipAttr = vipAttr;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public void setValidityTimes(Integer validityTimes) {
		this.validityTimes = validityTimes;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setRemainTimes(Float remainTimes) {
		this.remainTimes = remainTimes;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public void setProductSubType(String productSubType) {
		this.productSubType = productSubType;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public void setValidStoreIds(String validStoreIds) {
		this.validStoreIds = validStoreIds;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public void setAskForLeaveFlag(Integer askForLeaveFlag) {
		this.askForLeaveFlag = askForLeaveFlag;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setRelativeSD(String relativeSD) {
		this.relativeSD = relativeSD;
	}

	public void setCheckPrivilege(String checkPrivilege) {
		this.checkPrivilege = checkPrivilege;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
	

	
	
	
}
