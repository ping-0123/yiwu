package com.yinzhiwu.springmvc3.entity.yzw;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Embeddable
public class Contract {
	
	private String contractNo;
	
	private Integer validity;
	
	@Column(name="validity_times")
	private Integer validityTimes;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="startdate")
	private Date start;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="endDate")
	private Date end;
	
	@Column(name="remain_times")
	private Float remainTimes;
	
	@Column(name="product_type")
	private String type;
	
	@Column(name="product_subType")
	private String subType;
	
	@Column(name="valid_storeids")
	private String validStoreIds;
	
	@Column(name="checked_status")
	private String status;

	public Contract() {
	}

	public String getContractNo() {
		return contractNo;
	}

	public int getValidity() {
		return validity;
	}

	public int getValidityTimes() {
		return validityTimes;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public float getRemainTimes() {
		return remainTimes;
	}

	public String getType() {
		return type;
	}

	public String getSubType() {
		return subType;
	}

	public String getValidStoreIds() {
		return validStoreIds;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

	public void setValidityTimes(int validityTimes) {
		this.validityTimes = validityTimes;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void setRemainTimes(float remainTimes) {
		this.remainTimes = remainTimes;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public void setValidStoreIds(String validStoreIds) {
		this.validStoreIds = validStoreIds;
	}



	public String getStatus() {
		return status;
	}



	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
