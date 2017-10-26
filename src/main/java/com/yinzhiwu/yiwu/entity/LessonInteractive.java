package com.yinzhiwu.yiwu.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;

/**
*@Author ping
*@Time  创建时间:2017年10月26日上午10:00:04
*
*/

@SuppressWarnings("serial")
@Entity
@Table(name="yiwu_lesson_interactive")
public class LessonInteractive extends BaseEntity{
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="lesson_id",foreignKey=@ForeignKey(name="fk_lessoninteractive_lesson_id", value=ConstraintMode.NO_CONSTRAINT))
	private LessonYzw lesson;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="distributer_id", foreignKey=@ForeignKey(name="fk_lessoninteractive_distributer_id", value=ConstraintMode.NO_CONSTRAINT))
	private Distributer distributer;
	
	@NotNull
	private String contracNo;
	
	@Column(columnDefinition="boolean not null default false")
	private  Boolean appointed;
	
	@Column(columnDefinition="boolean not null default false")
	private  Boolean checkedIn;
	
	@Column(columnDefinition="boolean not null default false")
	private  Boolean praised;
	
	@Column(columnDefinition="boolean not null default false")
	private  Boolean firstCommented;
	
	@Column(columnDefinition="boolean not null default false")
	private  Boolean appendCommented;
	
	@Column(columnDefinition="boolean not null default false")
	private  Boolean workFinished;

	@Override
	public void init() {
		super.init();
		
		this.appointed=false;
		this.checkedIn=false;
		this.praised=false;
		this.firstCommented=false;
		this.appendCommented=false;
		this.workFinished=false;
	}
	
	public LessonYzw getLesson() {
		return lesson;
	}

	public Distributer getDistributer() {
		return distributer;
	}

	public String getContracNo() {
		return contracNo;
	}

	public Boolean getAppointed() {
		return appointed;
	}

	public Boolean getCheckedIn() {
		return checkedIn;
	}

	public Boolean getPraised() {
		return praised;
	}

	public Boolean getFirstCommented() {
		return firstCommented;
	}

	public Boolean getAppendCommented() {
		return appendCommented;
	}

	public Boolean getWorkFinished() {
		return workFinished;
	}

	public void setLesson(LessonYzw lesson) {
		this.lesson = lesson;
	}

	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
	}

	public void setContracNo(String contracNo) {
		this.contracNo = contracNo;
	}

	public void setAppointed(Boolean appointed) {
		this.appointed = appointed;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public void setPraised(Boolean praised) {
		this.praised = praised;
	}

	public void setFirstCommented(Boolean firstCommented) {
		this.firstCommented = firstCommented;
	}

	public void setAppendCommented(Boolean appendCommented) {
		this.appendCommented = appendCommented;
	}

	public void setWorkFinished(Boolean workFinished) {
		this.workFinished = workFinished;
	}

	
}
