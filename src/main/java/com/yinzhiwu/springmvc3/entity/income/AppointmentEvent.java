package com.yinzhiwu.springmvc3.entity.income;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;

@Entity
@DiscriminatorValue("AppointmentEvent")
public class AppointmentEvent extends AbstractAppointmentEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5532961506692083529L;

	public AppointmentEvent() {
		super();
	}

	public AppointmentEvent(Distributer distributer,  Float param, LessonYzw lesson) {
		super(distributer, EventType.APPOINTMENT, param, lesson);
	}
	
	

}
