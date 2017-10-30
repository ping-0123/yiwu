package com.yinzhiwu.yiwu.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yinzhiwu.yiwu.entity.yzw.LessonConnotation;

/**
*@Author ping
*@Time  创建时间:2017年10月25日上午11:25:29
*
*/

@SuppressWarnings("serial")
@Entity
@Table(name="yiwu_lesson_template")
public class LessonTemplate extends BaseEntity {
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_lessonTemplate_courseTemplate_id"))
	private CourseTemplate courseTemplate;
	
	private Integer ordinalNo;
	
	@Embedded
	private LessonConnotation connotation;

	public CourseTemplate getCourseTemplate() {
		return courseTemplate;
	}

	public Integer getOrdinalNo() {
		return ordinalNo;
	}

	public LessonConnotation getConnotation() {
		return connotation;
	}

	public void setCourseTemplate(CourseTemplate courseTemplate) {
		this.courseTemplate = courseTemplate;
	}

	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}

	public void setConnotation(LessonConnotation connotation) {
		this.connotation = connotation;
	}
	
	
	
}
