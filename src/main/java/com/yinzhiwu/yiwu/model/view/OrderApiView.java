package com.yinzhiwu.yiwu.model.view;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.Contract.ContractStatus;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.SubCourseType;
<<<<<<< HEAD
=======
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
>>>>>>> v1.2.6

@JsonInclude(value= Include.NON_NULL)
public class OrderApiView {

	private static Log logger = LogFactory.getLog(OrderApiView.class);

	private String id;
	private Integer productId;
	private String productName;
<<<<<<< HEAD
=======
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
>>>>>>> v1.2.6
	private Date payedDate;
	private String contractNo;
	private CourseType productType;
	private SubCourseType subCourseType;
<<<<<<< HEAD
	private int validityTimes;
	private int remainTimes;
	private int withHoldTimes;
=======
	private Integer validityTimes;
	private Integer remainTimes;
	private Integer withHoldTimes;
	private String validStores;
>>>>>>> v1.2.6
	// 合约开始日期
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date contractStart;
	// 合约结束日期
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date contractEnd;
	private  ContractStatus checkedStatus;
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
			this.withHoldTimes = contract.getWithHoldTimes().intValue();
			this.contractStart = contract.getStart();
			this.contractEnd = contract.getEnd();
			this.productType = contract.getType();
<<<<<<< HEAD
			this.subCourseType = contract.getSubType();
			this.checkedStatus = contract.getStatus().getStatus();
=======
			this.checkedStatus = contract.getStatus();
>>>>>>> v1.2.6
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

<<<<<<< HEAD
	public String getCheckedStatus() {
=======
	public Boolean geteContractStatus() {
		return eContractStatus;
	}

	public void seteContractStatus(Boolean eContractStatus) {
		this.eContractStatus = eContractStatus;
	}

	public ContractStatus getCheckedStatus() {
>>>>>>> v1.2.6
		return checkedStatus;
	}

	public void setCheckedStatus(ContractStatus checkedStatus) {
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

<<<<<<< HEAD
	public SubCourseType getSubCourseType() {
		return subCourseType;
	}

	public void setSubCourseType(SubCourseType subCourseType) {
		this.subCourseType = subCourseType;
	}

=======
>>>>>>> v1.2.6
	public Integer getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

<<<<<<< HEAD
	public Date getPayedDate() {
		return payedDate;
	}

=======
>>>>>>> v1.2.6
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

<<<<<<< HEAD
=======
	public Date getPayedDate() {
		return payedDate;
	}

>>>>>>> v1.2.6
	public void setPayedDate(Date payedDate) {
		this.payedDate = payedDate;
	}

<<<<<<< HEAD
	public OrderApiView(String id, Integer productId, String productName, Date payedDate, String contractNo,
			CourseType productType, SubCourseType subCourseType, int validityTimes, BigDecimal remainTimes, Short withHoldTimes,
			Date contractStart, Date contractEnd, String checkedStatus, String courseId) {
=======
	public SubCourseType getSubCourseType() {
		return subCourseType;
	}

	public void setSubCourseType(SubCourseType subCourseType) {
		this.subCourseType = subCourseType;
	}

	public OrderApiView(String id, Integer productId, String productName, Date payedDate, String contractNo,
			CourseType productType, SubCourseType subCourseType, Integer validityTimes, BigDecimal remainTimes, Short withHoldTimes,
			String validStoreIds, Date contractStart, Date contractEnd, ContractStatus checkedStatus, String courseId) {
>>>>>>> v1.2.6
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.payedDate = payedDate;
		this.contractNo = contractNo;
		this.productType = productType;
		this.subCourseType = subCourseType;
		this.validityTimes = validityTimes;
<<<<<<< HEAD
		this.remainTimes = remainTimes.intValue();
		this.withHoldTimes = withHoldTimes;
=======
		this.remainTimes =remainTimes==null?0:remainTimes.intValue();
		this.withHoldTimes = withHoldTimes.intValue();
		this.validStores  = validStoreIds;
>>>>>>> v1.2.6
		this.contractStart = contractStart;
		this.contractEnd = contractEnd;
		this.checkedStatus = checkedStatus;
		this.courseId = courseId;
	}

<<<<<<< HEAD
=======
	public String getValidStores() {
		return validStores;
	}

	public void setValidityTimes(Integer validityTimes) {
		this.validityTimes = validityTimes;
	}

	public void setRemainTimes(Integer remainTimes) {
		this.remainTimes = remainTimes;
	}

	public void setWithHoldTimes(Integer withHoldTimes) {
		this.withHoldTimes = withHoldTimes;
	}

	public void setValidStores(String validStores) {
		this.validStores = validStores;
	}

>>>>>>> v1.2.6
}
