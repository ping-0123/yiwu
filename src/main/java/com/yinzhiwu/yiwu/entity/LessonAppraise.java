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
import javax.validation.constraints.Size;

import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;

/**
*@Author ping
*@Time  创建时间:2017年10月9日下午5:07:39
*
*/

@SuppressWarnings("serial")
@Entity
@Table(name="yiwu_lesson_appraise")
public class LessonAppraise extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_praise_lesson_id", value=ConstraintMode.NO_CONSTRAINT))
	private LessonYzw lesson;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_praise_distributer_id", value=ConstraintMode.NO_CONSTRAINT))
	private Distributer distributer;
	
	@Column(length=32)
	@Enumerated(value=EnumType.STRING)
	private AppraiseType type;
	
	@Size(min=1, max=5)
	private Integer stars;
	
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="replied_id", foreignKey=@ForeignKey(name="fk_praise_replied_id", value= ConstraintMode.NO_CONSTRAINT))
	private LessonAppraise reAppraise;
	
	public static enum AppraiseType{
		FIRST,
		APPEND,
		REPLY
	}

	public LessonYzw getLesson() {
		return lesson;
	}

	public Distributer getDistributer() {
		return distributer;
	}

	public AppraiseType getType() {
		return type;
	}

	public String getComment() {
		return comment;
	}

	public void setLesson(LessonYzw lesson) {
		this.lesson = lesson;
	}

	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
	}

	public void setType(AppraiseType type) {
		this.type = type;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getStars() {
		return stars;
	}

	public LessonAppraise getReAppraise() {
		return reAppraise;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public void setReAppraise(LessonAppraise reAppraise) {
		this.reAppraise = reAppraise;
	}
	
	
}
