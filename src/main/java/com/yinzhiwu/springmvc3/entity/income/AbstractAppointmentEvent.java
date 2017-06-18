package com.yinzhiwu.springmvc3.entity.income;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;

@Entity
@DiscriminatorValue("AbstractAppointmentEvent")
public  abstract class AbstractAppointmentEvent extends IncomeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1879128304199492944L;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	private LessonYzw lesson;

	public LessonYzw getLesson() {
		return lesson;
	}

	public void setLesson(LessonYzw lesson) {
		this.lesson = lesson;
	}

	public AbstractAppointmentEvent() {
		super();
	}

	public AbstractAppointmentEvent(Distributer distributer, EventType type, Float param, LessonYzw lesson) {
		super(distributer, type, param);
		this.lesson = lesson;
	}

	
	
}
