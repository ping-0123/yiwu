package com.yinzhiwu.springmvc3.entity.income;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.entity.yzw.CheckInsYzw;

@Entity
@DiscriminatorValue("AfterAppointCheckInEvent")
public class AfterAppointCheckInEvent extends CheckInEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3379778955663790355L;

	public AfterAppointCheckInEvent() {
		super();
	}

	public AfterAppointCheckInEvent(Distributer distributer, EventType type, Float param, CheckInsYzw checkIn) {
		super(distributer, type, param, checkIn);
	}
	
	public AfterAppointCheckInEvent(Distributer distributer, Float param, CheckInsYzw checkIn){
		super(distributer, EventType.CHECK_IN_AFTER_APPOINTMENT, param,checkIn);
	}
	
}
