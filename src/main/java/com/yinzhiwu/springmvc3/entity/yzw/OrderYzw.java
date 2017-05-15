package com.yinzhiwu.springmvc3.entity.yzw;


import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="vorder")
public class OrderYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8473575190806095432L;

	@Id
	@GeneratedValue(generator="assigned")  
	@GenericGenerator(name="assigned", strategy = "assigned") 
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
	

	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="store_id", foreignKey=
			@ForeignKey(name="fk_order_store_id", value=ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw store;
	
	@Column(length=32, name="vip_attr")
	private String vipAttr;
	
	@Embedded
	private Contract contract;
	
//	@Column(name="checked_status", length=32)
//	private String checkedStatus;
	
//	@Column(length=32)
//	private String contractNo;
//	
//	@Column
//	private Integer validity;
//	
//	@Column(name="validity_times")
//	private Integer validityTimes;
//	
//	@Column
//	private Date startDate;
//	
//	@Column
//	private Date endDate;
//	
//	@Column(name="remain_times")
//	private Float remainTimes;
//	
//	@Column(length=32, name="product_type")
//	private String productType;
//	
//	@Column(length=32, name="product_subType")
//	private String productSubType;
	
//	@Column(name="valid_storeids")
//	private String validStoreIds;
	
	
	@Column(length=32)
	private String recommender;
	

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
	
	@Column(name="remain_hours")
	private Float remainHours;
	
	@Column(name="audit_flag")
	private Boolean isAuditedByFinance;
	
	@Column(name="e_contract_text")
	private Blob eContractText;
	
	@Column(name="e_contract_address")
	private String econtractAddress;
	
	@Column(name="e_contract_picture_flag")
	private int eContractPictureFlag;
	
	@Column(name="e_contract_status")
	private int eContractStatus;
	
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


	public Date getPayedDate() {
		return payedDate;
	}

	public CourseYzw getCourse() {
		return course;
	}

	public DepartmentYzw getStore() {
		return store;
	}

	public String getVipAttr() {
		return vipAttr;
	}

	public Contract getContract() {
		return contract;
	}

	public String getRecommender() {
		return recommender;
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


	public void setPayedDate(Date payedDate) {
		this.payedDate = payedDate;
	}

	public void setCourse(CourseYzw course) {
		this.course = course;
	}

	public void setStore(DepartmentYzw store) {
		this.store = store;
	}

	public void setVipAttr(String vipAttr) {
		this.vipAttr = vipAttr;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
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


	public CustomerYzw getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerYzw customer) {
		this.customer = customer;
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

	public String getEcontractAddress() {
		return econtractAddress;
	}

	public int geteContractPictureFlag() {
		return eContractPictureFlag;
	}

	public int geteContractStatus() {
		return eContractStatus;
	}

	public void setRemainHours(Float remainHours) {
		this.remainHours = remainHours;
	}

	public void setIsAuditedByFinance(Boolean isAuditedByFinance) {
		this.isAuditedByFinance = isAuditedByFinance;
	}

	public void seteContractText(Blob eContractText) {
		this.eContractText = eContractText;
	}

	public void setEcontractAddress(String econtractAddress) {
		this.econtractAddress = econtractAddress;
	}

	public void seteContractPictureFlag(int eContractPictureFlag) {
		this.eContractPictureFlag = eContractPictureFlag;
	}

	public void seteContractStatus(int eContractStatus) {
		this.eContractStatus = eContractStatus;
	}

	
	
	
}
