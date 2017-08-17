package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="event_type")
@DiscriminatorValue("AbstractAppointmentEvent")
public abstract class AbstractAppointmentEvent extends IncomeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1879128304199492944L;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
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
