package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;

public class OrderApiView {

	private Log LOG = LogFactory.getLog(OrderApiView.class);

	private String id;

	private String contractNo;

	private CourseType productType;

	private int validityTimes;

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

	private Boolean eContractStatus;

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
			this.contractStart = contract.getStart();
			this.contractEnd = contract.getEnd();
			this.productType = contract.getType();
			this.checkedStatus = contract.getStatus().getStatus();
		}

		CourseYzw course = o.getCourse();
		try {
			if (course != null) {
				this.courseId = course.getId();
				this.courseName = course.getName();
				this.courseStore = course.getStoreName();
				this.courseStartDate = course.getStartDate();
			}
		} catch (Exception e) {
			LOG.warn(e.getMessage());
		}
		this.eContractStatus = o.geteContractStatus();
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

	public Boolean geteContractStatus() {
		return eContractStatus;
	}

	public void seteContractStatus(Boolean eContractStatus) {
		this.eContractStatus = eContractStatus;
	}

	public String getCheckedStatus() {
		return checkedStatus;
	}

	public void setCheckedStatus(String checkedStatus) {
		this.checkedStatus = checkedStatus;
	}

}
