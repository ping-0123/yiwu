package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import com.yinzhiwu.yiwu.entity.CourseTemplate;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.SubCourseType;
import com.yinzhiwu.yiwu.model.view.CourseConnotationVO.CourseConnotationVOConverter;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

/**
* @author 作者 ping
* @Date 创建时间：2017年10月30日 下午10:18:11
*
*/

@MapedClass(CourseTemplate.class)
public class CourseTemplateVO {
	
	private Integer id;
	
	private String name;
	
	@MapedProperty(value="dance.id")
	private String danceId;
	
	@MapedProperty(value="dance.name")
	private String danceName;
	
	@MapedProperty(value="danceGrade.id")
	private Integer danceGradeId;
	
	@MapedProperty(value="danceGrade.name")
	private String danceGradeName;
	
	private CourseType courseType;
	
	private SubCourseType subCourseType;
	
	private Integer times;
	
	private Float hoursPerTime;
	
	private Integer minStudentCount;
	
	private Integer maxStudentCount;
	
	private Date effectiveStart;
	
	private Date effectiveEnd;
	
	@MapedProperty(value="usableDepartment.id")
	private Integer usableDepartmentId;
	
	@MapedProperty(value="usableDepartment.name")
	private Integer usableDepartmentName;
	
	@MapedProperty(value="provider.id")
	private Integer providerId;
	
	@MapedProperty(value="provider.name")
	private String providerName;
	
	
	@MapedProperty(ignored=true)
	private CourseConnotationVO connotation;

	public static final class CourseTemplateVOConverter extends AbstractConverter<CourseTemplate, CourseTemplateVO>{
		public static final CourseTemplateVOConverter INSTANCE = new CourseTemplateVOConverter();

		@Override
		public CourseTemplateVO fromPO(CourseTemplate po) {
			CourseTemplateVO vo = super.fromPO(po);
			vo.setConnotation(CourseConnotationVOConverter.INSTANCE.fromPO(po.getConnotation()));
			return vo;
		}

		@Override
		public CourseTemplate toPO(CourseTemplateVO vo) {
			CourseTemplate course =  super.toPO(vo);
			course.setConnotation(CourseConnotationVOConverter.INSTANCE.toPO(vo.getConnotation()));
			return course;
		}
		
		
	}
	
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

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Float getHoursPerTime() {
		return hoursPerTime;
	}

	public void setHoursPerTime(Float hoursPerTime) {
		this.hoursPerTime = hoursPerTime;
	}

	public Integer getMinStudentCount() {
		return minStudentCount;
	}

	public void setMinStudentCount(Integer minStudentCount) {
		this.minStudentCount = minStudentCount;
	}

	public Integer getMaxStudentCount() {
		return maxStudentCount;
	}

	public void setMaxStudentCount(Integer maxStudentCount) {
		this.maxStudentCount = maxStudentCount;
	}

	public Date getEffectiveStart() {
		return effectiveStart;
	}

	public void setEffectiveStart(Date effectiveStart) {
		this.effectiveStart = effectiveStart;
	}

	public Date getEffectiveEnd() {
		return effectiveEnd;
	}

	public void setEffectiveEnd(Date effectiveEnd) {
		this.effectiveEnd = effectiveEnd;
	}

	public Integer getUsableDepartmentId() {
		return usableDepartmentId;
	}

	public void setUsableDepartmentId(Integer usableDepartmentId) {
		this.usableDepartmentId = usableDepartmentId;
	}

	public Integer getUsableDepartmentName() {
		return usableDepartmentName;
	}

	public void setUsableDepartmentName(Integer usableDepartmentName) {
		this.usableDepartmentName = usableDepartmentName;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public CourseConnotationVO getConnotation() {
		return connotation;
	}

	public void setConnotation(CourseConnotationVO connotation) {
		this.connotation = connotation;
	}
	
	
	
}
