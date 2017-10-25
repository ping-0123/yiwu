package com.yinzhiwu.yiwu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;

/**
*@Author ping
*@Time  创建时间:2017年10月9日下午5:07:39
*
*/

@SuppressWarnings("serial")
@Entity
@Table(name="yiwu_lesson_comment")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class LessonComment extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_praise_lesson_id", value=ConstraintMode.NO_CONSTRAINT))
	private LessonYzw lesson;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_praise_distributer_id", value=ConstraintMode.NO_CONSTRAINT))
	private Distributer commenter;
	
	@Column(length=32)
	@Enumerated(value=EnumType.STRING)
	private AppraiseType type;
	
	private Integer stars;
	
	private String comment;
	
	private Date date;
	@ManyToOne
	@JoinColumn(name="replied_id", foreignKey=@ForeignKey(name="fk_praise_replied_id", value= ConstraintMode.NO_CONSTRAINT))
	private LessonComment reAppraise;
	
	public static enum AppraiseType{
		FIRST,
		APPEND,
		REPLY
	}

	
	
	public LessonYzw getLesson() {
		return lesson;
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

	public void setType(AppraiseType type) {
		this.type = type;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getStars() {
		return stars;
	}

	public LessonComment getReAppraise() {
		return reAppraise;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public void setReAppraise(LessonComment reAppraise) {
		this.reAppraise = reAppraise;
	}

	public Distributer getCommenter() {
		return commenter;
	}

	public void setCommenter(Distributer commenter) {
		this.commenter = commenter;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public void init() {
		super.init();
		if(this.date == null )
			this.date = super.getCreateTime();
	}
	
	
}
