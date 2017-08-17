package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;

/**
*@Author ping
*@Time  创建时间:2017年8月14日下午9:24:39
*
*/

@Entity
@PrimaryKeyJoinColumn(name="id")
public class BreakAppointmentEvent extends AbstractAppointmentEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4686468937773714117L;



	public BreakAppointmentEvent() {
		super();
	}

	public BreakAppointmentEvent(Distributer distributer, LessonYzw lesson) {
		super(distributer, EventType.BREAK_APPOINTMENT, 1f, lesson);
	}

	
}
