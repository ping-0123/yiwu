package com.yinzhiwu.springmvc3.entity.income;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.entity.yzw.CheckInsYzw;

@Entity
@DiscriminatorValue("WithoutAppointCheckInEvent")
public class WithoutAppointCheckInEvent extends CheckInEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1019536399141019878L;

	public WithoutAppointCheckInEvent() {
		super();
	}

	public WithoutAppointCheckInEvent(Distributer distributer, EventType type, Float param, CheckInsYzw checkIn) {
		super(distributer, type, param, checkIn);
	}

	public WithoutAppointCheckInEvent(Distributer distributer, Float param, CheckInsYzw checkIn){
		super(distributer, EventType.CHECK_IN_WITHOUT_APPOINTMENT, param,checkIn);
	}
}
