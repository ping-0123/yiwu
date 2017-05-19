package com.yinzhiwu.springmvc3.model.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.springmvc3.entity.yzw.Contract;
import com.yinzhiwu.springmvc3.entity.yzw.CourseYzw;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;

public class OrderApiView {
	
	private String id;
	
	private String contractNo;
	
	private String productType;
	
	private int validityTimes;
	
	//合约开始日期
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date contractStart; 
	
	//合约结束日期
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date contractEnd;
	
	private String courseId;
	
	private String courseName;
	
	private String courseStore;
	
	private Date courseStartDate;
	
	public OrderApiView(){};
	
	public OrderApiView(OrderYzw o){
		if(o == null)
			return;
		this.id = o.getId();
		Contract contract = o.getContract();
		if(contract != null){
			this.contractNo = contract.getContractNo();
			this.validityTimes = contract.getValidityTimes();
			this.contractStart = contract.getStart();
			this.contractEnd = contract.getEnd();
			this.productType = contract.getType();
		}
		
		CourseYzw course= o.getCourse();
		if(course != null){
			this.courseId = course.getId();
			this.courseName = course.getName();
			this.courseStore = course.getStoreName();
			this.courseStartDate = course.getStartDate();
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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Date getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	
	
	
}
