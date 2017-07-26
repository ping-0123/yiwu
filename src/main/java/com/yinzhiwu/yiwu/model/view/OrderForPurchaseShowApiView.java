package com.yinzhiwu.yiwu.model.view;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.Contract.ContractStatus;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.SubCourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;

/**
*@Author ping
*@Time  创建时间:2017年7月26日上午11:51:26
*
*/

public class OrderForPurchaseShowApiView {
	
	/**
	 * 订单建立之后，多少小时之内可以删除
	 */
	private static final int DELETABLE_AFTER_HOURS = 24;
	
	private String id;
	private String memberCard;
	private String productName;
	private Float markedPrice;
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
	private Integer salesmanId;
	private String salesmanName;
	private String comment;
	private boolean deletable;
	private List<StoreForOrderModifyApiView> validStores = new ArrayList<>();
	
	public static OrderForPurchaseShowApiView fromOrder(@NotNull OrderYzw order){
		if(order == null) throw new IllegalArgumentException("order cannot be null");
		OrderForPurchaseShowApiView v = new OrderForPurchaseShowApiView();
		v.setId(order.getId());
		CustomerYzw cust = order.getCustomer();
		if(cust != null)
			v.setMemberCard(cust.getMemberCard());
		ProductYzw prod = order.getProduct();
		if(prod != null)
			v.setProductName(order.getProduct().getName());
		v.markedPrice = order.getMarkedPrice();
		v.payedAmount = order.getPayedAmount();
		Contract contract = order.getContract();
		if(contract != null){
			v.contractNo = contract.getContractNo();
			v.courseType = contract.getType();
			v.subCourseType = contract.getSubType();
			v.status = contract.getStatus();
			v.startDate = contract.getStart();
			v.endDate = contract.getEnd();
			v.validTimes = contract.getValidity();
			v.remainTimes = (int) contract.getRemainTimes();
		}
		v.salesmanId = order.getCreateUserId();
		v.payedDate = order.getPayedDate();
		v.comment = order.getComments();
		v.setDeletable();
		return v;
		//TODO salesmanName, validStores 未设置
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
	public SubCourseType getSubCourseType() {
		return subCourseType;
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
	public void setSubCourseType(SubCourseType subCourseType) {
		this.subCourseType = subCourseType;
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

	public List<StoreForOrderModifyApiView> getValidStores() {
		return validStores;
	}

	public void setValidStores(List<StoreForOrderModifyApiView> validStores) {
		this.validStores = validStores;
	}

	public boolean isDeletable() {
		return deletable;
	}

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}
	
	public void setDeletable(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.payedDate);
		calendar.add(Calendar.HOUR, DELETABLE_AFTER_HOURS);
		if(calendar.after(Calendar.getInstance()))
			setDeletable(true);
		setDeletable(false);
	}

}
