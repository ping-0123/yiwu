package com.yinzhiwu.yiwu.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;

/**
*@Author ping
*@Time  创建时间:2017年10月9日下午4:59:29
*
*/

@SuppressWarnings("serial")
@Entity
@Table(name="yiwu_lesson_praise", indexes = {
		@Index(name="ind_lessonPraise_lessonId_distributerId", columnList="lesson_id,distributer_id")
})
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class LessonPraise extends BaseEntity {

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_praise_lesson_id", value=ConstraintMode.NO_CONSTRAINT))
	private LessonYzw lesson;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_praise_distributer_id"))
	private Distributer distributer;
	
	@Column(columnDefinition="boolean default false")
	private Boolean canceled;
	
	
	
	@Override
	public void init() {
		super.init();
		canceled = false;
	}

	public LessonYzw getLesson() {
		return lesson;
	}

	public Distributer getDistributer() {
		return distributer;
	}

	public void setLesson(LessonYzw lesson) {
		this.lesson = lesson;
	}

	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
	}

	/**
	 * @return the canceled
	 */
	public Boolean getCanceled() {
		return canceled;
	}

	/**
	 * @param canceled the canceled to set
	 */
	public void setCanceled(Boolean canceled) {
		this.canceled = canceled;
	}

	
	
	
}
