package com.yinzhiwu.yiwu.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;

/**
*@Author ping
*@Time  创建时间:2017年10月7日下午3:00:44
*
*/

@SuppressWarnings("serial")
@Entity
@Table(name="yiwu_media")
public class Media extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_media_coach_id", value=ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw coach;
	
	@Column(length=128)
	private String uri;
	
	@Enumerated(value=EnumType.STRING)
	private MediaType type;
	
	@Enumerated(value=EnumType.STRING)
	private MediaTag tag;
	
	private String text;
	
	@Column(columnDefinition="boolean not null default false")
	private boolean coverage;
	public static enum MediaType{
		VIDEO,
		AUDIO,
		IMAGE,
		TEXT,
		FILE;
	}
	
	public static enum MediaTag{
		DAILY,
		CERTIFICATE,
		DANCE;
	}

	public EmployeeYzw getCoach() {
		return coach;
	}

	public String getUri() {
		return uri;
	}

	public MediaType getType() {
		return type;
	}

	public MediaTag getTag() {
		return tag;
	}

	public String getText() {
		return text;
	}

	public void setCoach(EmployeeYzw coach) {
		this.coach = coach;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setType(MediaType type) {
		this.type = type;
	}

	public void setTag(MediaTag tag) {
		this.tag = tag;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCoverage() {
		return coverage;
	}

	public void setCoverage(boolean coverage) {
		this.coverage = coverage;
	}
	
	
}
