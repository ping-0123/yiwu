package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;

/**
*@Author ping
*@Time  创建时间:2017年8月12日下午4:03:37
*
*/

@Entity
@DiscriminatorValue("CheckInWithoutAppointEvent")
public class CheckInWithoutAppointEvent extends CheckInEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8513241558566265626L;
	public CheckInWithoutAppointEvent() {
	}

	public CheckInWithoutAppointEvent(Distributer distributer, LessonCheckInYzw checkIn) {
		super(distributer, EventType.CHECK_IN_WITHOUT_APPOINTMENT,1f,checkIn);
	}

	public CheckInWithoutAppointEvent(LessonCheckInYzw checkIn) {
		super(checkIn);
	}

}
