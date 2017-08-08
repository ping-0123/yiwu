package com.yinzhiwu.yiwu.web.purchase.dto;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.SubCourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.CustomerAgeType;

import io.swagger.annotations.ApiModelProperty;

/**
*@Author ping
*@Time  创建时间:2017年7月29日下午2:12:03
*
*/

public class OrderSaveDto {
	private String orderId;
	
	@Min(1)
	@ApiModelProperty(required=true)
	private Integer employeeId;
	
	@Min(1)
	@ApiModelProperty(required=true)
	private Integer storeId;
	
	@NotNull
	@ApiModelProperty(required=true)
	private String validStoreIds;
	
	@Min(0)
	@ApiModelProperty(required=true)
	private Integer customerId;
	
	@NotNull
	@ApiModelProperty(required=true)
	private CustomerAgeType ageType;
	
	@Min(0)
	@ApiModelProperty(required=true)
	private Integer productId;
	
	
	@NotNull
	@ApiModelProperty(required=true)
	private SubCourseType middleProductType;
	
	@Min(1)
	@ApiModelProperty(required=true)
	private Integer quantity;
	
	@Min(1)
	@ApiModelProperty(required=true)
	private Integer usefulTimes;
	
	@Min(0)
	@ApiModelProperty(required=true)
	private Float payedAmount;
	
	private Date payedDate;
	
	@Future
	private Date contractStartDate;

	public String getOrderId() {
		return orderId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public String getValidStoreIds() {
		return validStoreIds;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public CustomerAgeType getAgeType() {
		return ageType;
	}

	public Integer getProductId() {
		return productId;
	}

	public SubCourseType getMiddleProductType() {
		return middleProductType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Integer getUsefulTimes() {
		return usefulTimes;
	}

	public Float getPayedAmount() {
		return payedAmount;
	}

	public Date getPayedDate() {
		return payedDate;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public void setValidStoreIds(String validStoreIds) {
		this.validStoreIds = validStoreIds;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public void setAgeType(CustomerAgeType ageType) {
		this.ageType = ageType;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setMiddleProductType(SubCourseType middleProductType) {
		this.middleProductType = middleProductType;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setUsefulTimes(Integer usefulTimes) {
		this.usefulTimes = usefulTimes;
	}

	public void setPayedAmount(Float payedAmount) {
		this.payedAmount = payedAmount;
	}

	public void setPayedDate(Date payedDate) {
		this.payedDate = payedDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	

	
	
}
