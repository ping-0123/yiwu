package com.yinzhiwu.yiwu.web.operating.view;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.ProductCardType;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.UsableRangeType;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.enums.SubCourseType;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月26日 下午11:16:11
*
*/

@MapedClass(ProductYzw.class)
public class ProductView {
	
	private Integer id;
	
	private String name;
	
	private ProductCardType cardType;
	
	private Integer markedPrice;

	private Short usefulLife;

	private Short usefulTimes;

	private Boolean isObsolete;

	private String dyRCP;

	private Short maxLeaveTimes;
	
	private String visableDepartmentIds;
	
	private Float usefulHours;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date endDate;
	
	@MapedProperty("contractType.id")
	private Integer contractTypeId;
	
	@MapedProperty("contractType.contractType")
	private CourseType contractType;
	
	@MapedProperty("dance.id")
	private String danceId;
	
	@MapedProperty("dance.name")
	private String danceName;
	
	@MapedProperty("danceGrade.id")
	private Integer danceGradeId;
	
	@MapedProperty("danceGrade.name")
	private String danceGradeName;
	
	private CourseType courseType;
	
	private SubCourseType subCourseType;
	
	private UsableRangeType usableRangeType;
	
	private String usableDepartments;
	
	@MapedProperty("company.id")
	private Integer companyId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCardType getCardType() {
		return cardType;
	}

	public void setCardType(ProductCardType cardType) {
		this.cardType = cardType;
	}

	public Integer getMarkedPrice() {
		return markedPrice;
	}

	public void setMarkedPrice(Integer markedPrice) {
		this.markedPrice = markedPrice;
	}

	public Short getUsefulLife() {
		return usefulLife;
	}

	public void setUsefulLife(Short usefulLife) {
		this.usefulLife = usefulLife;
	}

	public Short getUsefulTimes() {
		return usefulTimes;
	}

	public void setUsefulTimes(Short usefulTimes) {
		this.usefulTimes = usefulTimes;
	}

	public Boolean getIsObsolete() {
		return isObsolete;
	}

	public void setIsObsolete(Boolean isObsolete) {
		this.isObsolete = isObsolete;
	}

	public String getDyRCP() {
		return dyRCP;
	}

	public void setDyRCP(String dyRCP) {
		this.dyRCP = dyRCP;
	}

	public Short getMaxLeaveTimes() {
		return maxLeaveTimes;
	}

	public void setMaxLeaveTimes(Short maxLeaveTimes) {
		this.maxLeaveTimes = maxLeaveTimes;
	}

	public String getVisableDepartmentIds() {
		return visableDepartmentIds;
	}

	public void setVisableDepartmentIds(String visableDepartmentIds) {
		this.visableDepartmentIds = visableDepartmentIds;
	}

	public Float getUsefulHours() {
		return usefulHours;
	}

	public void setUsefulHours(Float usefulHours) {
		this.usefulHours = usefulHours;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getContractTypeId() {
		return contractTypeId;
	}

	public void setContractTypeId(Integer contractTypeId) {
		this.contractTypeId = contractTypeId;
	}

	public CourseType getContractType() {
		return contractType;
	}

	public void setContractType(CourseType contractType) {
		this.contractType = contractType;
	}

	public String getDanceId() {
		return danceId;
	}

	public void setDanceId(String danceId) {
		this.danceId = danceId;
	}

	public String getDanceName() {
		return danceName;
	}

	public void setDanceName(String danceName) {
		this.danceName = danceName;
	}

	public Integer getDanceGradeId() {
		return danceGradeId;
	}

	public void setDanceGradeId(Integer danceGradeId) {
		this.danceGradeId = danceGradeId;
	}

	public String getDanceGradeName() {
		return danceGradeName;
	}

	public void setDanceGradeName(String danceGradeName) {
		this.danceGradeName = danceGradeName;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public SubCourseType getSubCourseType() {
		return subCourseType;
	}

	public void setSubCourseType(SubCourseType subCourseType) {
		this.subCourseType = subCourseType;
	}

	public UsableRangeType getUsableRangeType() {
		return usableRangeType;
	}

	public void setUsableRangeType(UsableRangeType usableRangeType) {
		this.usableRangeType = usableRangeType;
	}

	public String getUsableDepartments() {
		return usableDepartments;
	}

	public void setUsableDepartments(String usableDepartments) {
		this.usableDepartments = usableDepartments;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	public static final class ProductViewConverter extends AbstractConverter<ProductYzw, ProductView>{
		public static final ProductViewConverter INSTANCE = new ProductViewConverter();
		
		private DepartmentYzwService deptService = SpringUtils.getBean(DepartmentYzwService.class);
		
		@Override
		public ProductView fromPO(ProductYzw po) {
			ProductView view =  super.fromPO(po);
			if(StringUtils.hasText(po.getUsableDepartments())){
				view.setUsableDepartments(
						deptService.translateCommaSeparateIdsToNames(po.getUsableDepartments()));
			}
			
			return view;
		}
		
		
	}
}
