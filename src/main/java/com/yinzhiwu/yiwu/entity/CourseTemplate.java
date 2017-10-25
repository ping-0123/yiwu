package com.yinzhiwu.yiwu.entity;

import javax.persistence.ConstraintMode;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yinzhiwu.yiwu.entity.yzw.Connotation;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.SubCourseType;
import com.yinzhiwu.yiwu.entity.yzw.DanceGradeYzw;
import com.yinzhiwu.yiwu.entity.yzw.DanceYzw;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;

/**
*@Author ping
*@Time  创建时间:2017年10月25日上午11:24:59
*
*/

@SuppressWarnings("serial")
@Entity
@Table(name="yiwu_course_template")
public class CourseTemplate extends BaseEntity {
	
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dance_id", foreignKey = @ForeignKey(name = "fk_courseTemplate_dance_id", value = ConstraintMode.NO_CONSTRAINT))
	private DanceYzw dance;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="danceGrade_id", foreignKey=@ForeignKey(name="fk_courseTemplate_danceGrade_id",value=ConstraintMode.NO_CONSTRAINT))
	private DanceGradeYzw danceGrade;
	
	private CourseType courseType;
	
	private SubCourseType subCourseType;
	
	private Integer times;
	
	private Float hoursPerTime;
	
	private Integer minStudentCount;
	
	private DepartmentYzw usableRange;
	
	@Embedded
	private Connotation connotation;

	public String getName() {
		return name;
	}

	public DanceYzw getDance() {
		return dance;
	}

	public DanceGradeYzw getDanceGrade() {
		return danceGrade;
	}

	public Integer getTimes() {
		return times;
	}

	public Float getHoursPerTime() {
		return hoursPerTime;
	}

	public Integer getMinStudentCount() {
		return minStudentCount;
	}

	public Connotation getConnotation() {
		return connotation;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDance(DanceYzw dance) {
		this.dance = dance;
	}

	public void setDanceGrade(DanceGradeYzw danceGrade) {
		this.danceGrade = danceGrade;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public void setHoursPerTime(Float hoursPerTime) {
		this.hoursPerTime = hoursPerTime;
	}

	public void setMinStudentCount(Integer minStudentCount) {
		this.minStudentCount = minStudentCount;
	}

	public void setConnotation(Connotation connotation) {
		this.connotation = connotation;
	}

	public DepartmentYzw getUsableRange() {
		return usableRange;
	}

	public void setUsableRange(DepartmentYzw usableRange) {
		this.usableRange = usableRange;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public SubCourseType getSubCourseType() {
		return subCourseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public void setSubCourseType(SubCourseType subCourseType) {
		this.subCourseType = subCourseType;
	}
	
	
}
