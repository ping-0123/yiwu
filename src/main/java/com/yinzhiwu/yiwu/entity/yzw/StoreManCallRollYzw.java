package com.yinzhiwu.yiwu.entity.yzw;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vstore_callroll")
public class StoreManCallRollYzw extends BaseYzwEntity {

	private static final long serialVersionUID = -4834495028899986109L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	private LessonYzw lesson;

	@Column(length = 32)
	private String memberCard;

	@Column(name="called")
	private Boolean called;

	@Column
	private String unCalledrollReason;

	
	
	@Override
	public void init() {
		super.init();
		if(null ==called)
			called =true;
	}

	public Integer getId() {
		return id;
	}

	public LessonYzw getLesson() {
		return lesson;
	}

	public String getMemberCard() {
		return memberCard;
	}

	public Boolean getCalled() {
		return called;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLesson(LessonYzw lesson) {
		this.lesson = lesson;
	}

	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public void setCalled(Boolean called) {
		this.called = called;
	}

	public String getUnCalledrollReason() {
		return unCalledrollReason;
	}

	public void setUnCalledrollReason(String unCalledrollReason) {
		this.unCalledrollReason = unCalledrollReason;
	}

	

	
}
