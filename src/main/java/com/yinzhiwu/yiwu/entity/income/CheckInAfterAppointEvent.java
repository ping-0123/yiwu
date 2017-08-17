package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.yzw.CheckInsYzw;

@Entity
@DiscriminatorValue("AfterAppointCheckInEvent")
public class CheckInAfterAppointEvent extends CheckInEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3379778955663790355L;

	public CheckInAfterAppointEvent() {
	}

	public CheckInAfterAppointEvent(Distributer distributer, EventType type, Float param, CheckInsYzw checkIn) {
		super(distributer, type, param, checkIn);
	}

	public CheckInAfterAppointEvent(Distributer distributer, Float param, CheckInsYzw checkIn) {
		super(distributer, EventType.CHECK_IN_AFTER_APPOINTMENT, param, checkIn);
	}

	public CheckInAfterAppointEvent(Distributer distributer, CheckInsYzw checkIn){
		super(distributer, EventType.CHECK_IN_AFTER_APPOINTMENT, 1f, checkIn);
	}
}