package com.yinzhiwu.yiwu.web.operating.view;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.Contract.ContractStatus;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.enums.SubCourseType;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月26日 下午5:22:27
*
*/

@MapedClass(Contract.class)
public class ContractView {
	
	private String contractNo;
	
	private Integer validity;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date start;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date end;
	
	private Integer validityTimes;
	
	private BigDecimal remainTimes;
	
	private Short withHoldTimes;
	
	private CourseType type;
	
	private SubCourseType subType;
	
	private String validStoreIds;
	
	private ContractStatus status;
	
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Integer getValidityTimes() {
		return validityTimes;
	}

	public void setValidityTimes(Integer validityTimes) {
		this.validityTimes = validityTimes;
	}

	public BigDecimal getRemainTimes() {
		return remainTimes;
	}

	public void setRemainTimes(BigDecimal remainTimes) {
		this.remainTimes = remainTimes;
	}

	public Short getWithHoldTimes() {
		return withHoldTimes;
	}

	public void setWithHoldTimes(Short withHoldTimes) {
		this.withHoldTimes = withHoldTimes;
	}

	public CourseType getType() {
		return type;
	}

	public void setType(CourseType type) {
		this.type = type;
	}

	public SubCourseType getSubType() {
		return subType;
	}

	public void setSubType(SubCourseType subType) {
		this.subType = subType;
	}

	public String getValidStoreIds() {
		return validStoreIds;
	}

	public void setValidStoreIds(String validStoreIds) {
		this.validStoreIds = validStoreIds;
	}

	public ContractStatus getStatus() {
		return status;
	}

	public void setStatus(ContractStatus status) {
		this.status = status;
	}

	
}
