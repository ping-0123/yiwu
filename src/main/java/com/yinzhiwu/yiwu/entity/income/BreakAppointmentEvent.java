package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;

/**
*@Author ping
*@Time  创建时间:2017年8月14日下午9:24:39
*
*/

@Entity
@DiscriminatorValue("BreakAppointmentEvent")
public class BreakAppointmentEvent extends AbstractAppointmentEvent {

	/**
	 * 
	 */
	

	private static final long serialVersionUID = 1L;

	public BreakAppointmentEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BreakAppointmentEvent(Distributer distributer, LessonYzw lesson) {
		super(distributer, EventType.BREAK_APPOINTMENT, 1f, lesson);
	}

	
}
