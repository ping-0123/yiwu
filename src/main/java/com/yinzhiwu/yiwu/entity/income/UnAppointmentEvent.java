package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;

@Entity
@DiscriminatorValue("UnAppointmentEvent")
public class UnAppointmentEvent extends AbstractAppointmentEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8627872947621556914L;

	public UnAppointmentEvent() {
		super();
	}

	public UnAppointmentEvent(Distributer distributer, Float param, LessonYzw lesson) {
		super(distributer, EventType.UN_APPOINTMENT, param, lesson);
	}

}
