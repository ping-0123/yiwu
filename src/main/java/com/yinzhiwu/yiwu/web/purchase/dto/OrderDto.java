package com.yinzhiwu.yiwu.web.purchase.dto;


import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.Contract.ContractStatus;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.SubCourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.ProductCardType;

import io.swagger.annotations.ApiModelProperty;

/**
*@Author ping
*@Time  创建时间:2017年7月26日上午11:51:26
*
*/

public class OrderDto {
	
	/**
	 * 订单建立之后，多少小时之内可以删除
	 */
	
	
	private String id;
	@Min(1)
	@ApiModelProperty(value="客户Id", required=true)
	private Integer customerId;
	private String memberCard;
	@Min(1)
	@ApiModelProperty(required=true)
	private Integer productId;
	private String productName;
	private Float markedPrice;
	private ProductCardType productCardType;
	private String productCardTypeName;
	@Min(1)
	@ApiModelProperty(value="购买产品的数量", required=true)
	private Integer count;
	@ApiModelProperty(value="折扣")
	private Float discount;
	private Float payedAmount;
	private String contractNo;
	private CourseType courseType;
	private SubCourseType subCourseType;
	private ContractStatus status;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date payedDate;
	private Integer validTimes;
	private Integer remainTimes;
	
	@Min(1)
	@ApiModelProperty(required=true)
	private Integer salesmanId;
	private String salesmanName;
	private String comment;
	
	@Min(1)
	@ApiModelProperty(value="交易门店", required=true)
	private Integer storeId;
	
	@NotNull
	@ApiModelProperty(required=true)
	private String validStoresIds;
	private boolean modifiable;
	
	
	public static OrderDto fromOrder(@NotNull OrderYzw order){
		if(order == null) throw new IllegalArgumentException("order cannot be null");
		OrderDto v = new OrderDto();
		v.setId(order.getId());
		CustomerYzw cust = order.getCustomer();
		if(cust != null){
			v.setCustomerId(cust.getId());
			v.setMemberCard(cust.getMemberCard());
		}
		ProductYzw prod = order.getProduct();
		if(prod != null){
			v.setProductId(prod.getId());
			v.setProductName(order.getProduct().getName());
			v.setProductCardType(prod.getCardType());
			v.setProductCardTypeName(prod.getCardType().getName());
		}
		v.markedPrice = order.getMarkedPrice();
		v.setCount(order.getCount());
		v.setDiscount(order.getDiscount());
		v.payedAmount = order.getPayedAmount();
		Contract contract = order.getContract();
		if(contract != null){
			v.contractNo = contract.getContractNo();
			v.courseType = contract.getType();
			v.subCourseType = contract.getSubType();
			v.status = contract.getStatus();
			v.startDate = contract.getStart();
			v.endDate = contract.getEnd();
			v.validTimes = contract.getValidityTimes();
			v.remainTimes =  contract.getRemainTimes().intValue();
			v.setValidStoresIds(contract.getValidStoreIds());
		}
		v.salesmanId = order.getCreateUserId();
		v.payedDate = order.getPayedDate();
		v.comment = order.getComments();
		v.setModifiable();
		return v;
		//TODO salesmanName
	}
	
	public String getId() {
		return id;
	}
	public String getMemberCard() {
		return memberCard;
	}
	public String getProductName() {
		return productName;
	}
	public Float getMarkedPrice() {
		return markedPrice;
	}
	public Float getPayedAmount() {
		return payedAmount;
	}
	public String getContractNo() {
		return contractNo;
	}
	public CourseType getCourseType() {
		return courseType;
	}
	public ContractStatus getStatus() {
		return status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public Date getPayedDate() {
		return payedDate;
	}
	public Integer getValidTimes() {
		return validTimes;
	}
	public Integer getRemainTimes() {
		return remainTimes;
	}
	public String getSalesmanName() {
		return salesmanName;
	}
	public String getComment() {
		return comment;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setMarkedPrice(Float markedPrice) {
		this.markedPrice = markedPrice;
	}
	public void setPayedAmount(Float payedAmount) {
		this.payedAmount = payedAmount;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}
	public void setStatus(ContractStatus status) {
		this.status = status;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setPayedDate(Date payedDate) {
		this.payedDate = payedDate;
	}
	public void setValidTimes(Integer validTimes) {
		this.validTimes = validTimes;
	}
	public void setRemainTimes(Integer remainTimes) {
		this.remainTimes = remainTimes;
	}
	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Integer salesmanId) {
		this.salesmanId = salesmanId;
	}


	public void setModifiable(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.payedDate);
		calendar.add(Calendar.HOUR, OrderYzw.DELETABLE_AFTER_HOURS);
		if(calendar.after(Calendar.getInstance()))
			setModifiable(true);
		setModifiable(false);
	}

	public SubCourseType getSubCourseType() {
		return subCourseType;
	}

	public String getValidStoresIds() {
		return validStoresIds;
	}

	public void setSubCourseType(SubCourseType subCourseType) {
		this.subCourseType = subCourseType;
	}

	public void setValidStoresIds(String validStoresIds) {
		this.validStoresIds = validStoresIds;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Integer getProductId() {
		return productId;
	}

	public Integer getCount() {
		return count;
	}

	public Float getDiscount() {
		return discount;
	}

	public boolean isModifiable() {
		return modifiable;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public void setModifiable(boolean modifiable) {
		this.modifiable = modifiable;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public ProductCardType getProductCardType() {
		return productCardType;
	}

	public void setProductCardType(ProductCardType productCardType) {
		this.productCardType = productCardType;
	}

	public String getProductCardTypeName() {
		return productCardTypeName;
	}

	public void setProductCardTypeName(String productCardTypeName) {
		this.productCardTypeName = productCardTypeName;
	}

}
