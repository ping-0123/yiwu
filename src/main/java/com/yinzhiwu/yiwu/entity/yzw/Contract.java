package com.yinzhiwu.yiwu.entity.yzw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.enums.CourseType.CourseTypeConverter;
import com.yinzhiwu.yiwu.enums.SubCourseType;
import com.yinzhiwu.yiwu.enums.SubCourseType.SubCourseTypeConverter;

@Embeddable
public class Contract {
	public static final String STORE_ID_SEPARATION = ";";
	public enum ContractStatus {
		UN_PAYED("未付款"),
		UN_VERIFIED("未确认"),
		VERIFIED("已确认"),
		UN_CHECKED("未审核"), 
		CHECKED("已审核"), 
		UN_PASSED("未通过"), 
		LEFT("请假"), 
		RETURNED_PREMIUM("退费"), 
		FORBIDDEN("禁用"), 
		EXPIRED("到期"),
		UN_KNOWN("");

		
		private final String status;

		public String getStatus() {
			return status;
		}

		private ContractStatus(String status) {
			this.status = status;
		}
		
		public static List<ContractStatus> getUnExpiredStatus(){
			List<ContractStatus> status = new ArrayList<Contract.ContractStatus>();
			status.add(ContractStatus.UN_VERIFIED);
			status.add(ContractStatus.VERIFIED);
			status.add(ContractStatus.UN_CHECKED);
			status.add(ContractStatus.CHECKED);
			
			return status;
			
		}

		public static ContractStatus fromStatus(String status) {
			switch (status) {
			case "未付款":
				return ContractStatus.UN_PAYED;
			case "未确认":
				return ContractStatus.UN_VERIFIED;
			case "已确认":
				return ContractStatus.VERIFIED;
			case "未审核":
				return ContractStatus.UN_CHECKED;
			case "已审核":
				return ContractStatus.CHECKED;
			case "未通过":
				return ContractStatus.UN_PASSED;
			case "请假":
				return ContractStatus.LEFT;
			case "退费":
				return ContractStatus.RETURNED_PREMIUM;
			case "禁用":
				return ContractStatus.FORBIDDEN;
			case "到期":
				return ContractStatus.EXPIRED;
			case "":
				return ContractStatus.UN_KNOWN;
			default:
				throw new UnsupportedOperationException(status + "is not supported");
			}
		}
	}

	@Converter
	public static class ContractStatusConverter implements AttributeConverter<ContractStatus, String> {

		@Override
		public String convertToDatabaseColumn(ContractStatus arg0) {
			if (arg0 == null)
				return null;
			return arg0.getStatus();
		}

		@Override
		public ContractStatus convertToEntityAttribute(String arg0) {
			if (arg0 == null || "".equals(arg0.trim()))
				return null;
			return ContractStatus.fromStatus(arg0);
		}

	}

	private String contractNo;

	private Integer validity;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	@Column(name = "startdate")
	private Date start;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	@Column(name = "endDate")
	private Date end;
	
	@Column(name = "validity_times")
	private Integer validityTimes;
	@Column(name = "remain_times")
	private BigDecimal remainTimes;
	@Column(name="with_hold_times")
	private Short withHoldTimes = 0;

	@Column(name = "product_type", length=32)
	@Convert(converter=CourseTypeConverter.class)
	private CourseType type;

	@Column(name = "product_subType", length=32)
	@Convert(converter=SubCourseTypeConverter.class)
	private SubCourseType subType;

	@Column(name = "valid_storeids")
	private String validStoreIds;

	@Column(name = "checked_status")
	@Convert(converter = ContractStatusConverter.class)
	private ContractStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "fk_order_course_id", value = ConstraintMode.NO_CONSTRAINT))
	private CourseYzw course;
	
	public Contract() {
	}

	public String getContractNo() {
		return contractNo;
	}


	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}


	public CourseType getType() {
		return type;
	}

	public SubCourseType getSubType() {
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


	public void setType(CourseType type) {
		this.type = type;
	}

	public void setSubType(SubCourseType subType) {
		this.subType = subType;
	}

	public void setValidStoreIds(String validStoreIds) {
		this.validStoreIds = validStoreIds;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public ContractStatus getStatus() {
		return status;
	}

	public void setValidityTimes(Integer validityTimes) {
		this.validityTimes = validityTimes;
	}

	public void setStatus(ContractStatus status) {
		this.status = status;
	}

	public Integer getValidity() {
		return validity;
	}

	public Integer getValidityTimes() {
		return validityTimes;
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

	public CourseYzw getCourse() {
		return course;
	}

	public void setCourse(CourseYzw course) {
		this.course = course;
	}


}
