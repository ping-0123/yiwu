package com.yinzhiwu.yiwu.model.view;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.SubCourseType;

public class OrderApiView {

	private static Log logger = LogFactory.getLog(OrderApiView.class);

	private String id;
	private Integer productId;
	private String productName;
	private Date payedDate;
	private String contractNo;
	private CourseType productType;
	private SubCourseType subCourseType;
	private int validityTimes;
	private int remainTimes;
	private int withHoldTimes;
	// 合约开始日期
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date contractStart;
	// 合约结束日期
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date contractEnd;
	private String checkedStatus;
	private String courseId;
	private String courseName;
	private String courseStore;
	private Date courseStartDate;

	public OrderApiView() {
	};

	public OrderApiView(OrderYzw o) {
		if (o == null)
			return;
		this.id = o.getId();
		Contract contract = o.getContract();
		if (contract != null) {
			this.contractNo = contract.getContractNo();
			this.validityTimes = contract.getValidityTimes();
			this.remainTimes = contract.getRemainTimes().intValue();
			this.withHoldTimes = contract.getWithHoldTimes();
			this.contractStart = contract.getStart();
			this.contractEnd = contract.getEnd();
			this.productType = contract.getType();
			this.subCourseType = contract.getSubType();
			this.checkedStatus = contract.getStatus().getStatus();
		}

		CourseYzw course = contract.getCourse();
		try {
			if (course != null) {
				this.courseId = course.getId();
				this.courseName = course.getName();
				this.courseStore = course.getStoreName();
				this.courseStartDate = course.getStartDate();
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}

	public String getId() {
		return id;
	}

	public String getContractNo() {
		return contractNo;
	}

	public int getValidityTimes() {
		return validityTimes;
	}

	public Date getContractStart() {
		return contractStart;
	}

	public Date getContractEnd() {
		return contractEnd;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCourseStore() {
		return courseStore;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public void setValidityTimes(int validityTimes) {
		this.validityTimes = validityTimes;
	}

	public void setContractStart(Date contractStart) {
		this.contractStart = contractStart;
	}

	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setCourseStore(String courseStore) {
		this.courseStore = courseStore;
	}

	public CourseType getProductType() {
		return productType;
	}

	public void setProductType(CourseType productType) {
		this.productType = productType;
	}

	public Date getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public String getCheckedStatus() {
		return checkedStatus;
	}

	public void setCheckedStatus(String checkedStatus) {
		this.checkedStatus = checkedStatus;
	}

	public int getRemainTimes() {
		return remainTimes;
	}

	public int getWithHoldTimes() {
		return withHoldTimes;
	}

	public void setRemainTimes(int remainTimes) {
		this.remainTimes = remainTimes;
	}

	public void setWithHoldTimes(int withHoldTimes) {
		this.withHoldTimes = withHoldTimes;
	}

	public SubCourseType getSubCourseType() {
		return subCourseType;
	}

	public void setSubCourseType(SubCourseType subCourseType) {
		this.subCourseType = subCourseType;
	}

	public Integer getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public Date getPayedDate() {
		return payedDate;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPayedDate(Date payedDate) {
		this.payedDate = payedDate;
	}

	public OrderApiView(String id, Integer productId, String productName, Date payedDate, String contractNo,
			CourseType productType, SubCourseType subCourseType, int validityTimes, BigDecimal remainTimes, Short withHoldTimes,
			Date contractStart, Date contractEnd, String checkedStatus, String courseId) {
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.payedDate = payedDate;
		this.contractNo = contractNo;
		this.productType = productType;
		this.subCourseType = subCourseType;
		this.validityTimes = validityTimes;
		this.remainTimes = remainTimes.intValue();
		this.withHoldTimes = withHoldTimes;
		this.contractStart = contractStart;
		this.contractEnd = contractEnd;
		this.checkedStatus = checkedStatus;
		this.courseId = courseId;
	}

}
