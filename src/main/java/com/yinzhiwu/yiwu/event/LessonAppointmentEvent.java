package com.yinzhiwu.yiwu.event;

import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;

/**
*@Author ping
*@Time  创建时间:2017年11月9日下午2:56:14
*
*/

@SuppressWarnings("serial")
public class LessonAppointmentEvent extends LessonInteractiveEvent implements IncomeEvent {

	public LessonAppointmentEvent(LessonAppointmentYzw appointment) {
		super(appointment);
	}

	@Override
	public IncomeEventType getType() {
		switch (((LessonAppointmentYzw)getSource()).getStatus()) {
		case APPONTED:
			return IncomeEventType.LESSON_APPOINTMENT;
		case UN_APOINTED:
			return IncomeEventType.CANCEL_LESSON_APPOINTMENT;
		case BREAKED:
			return IncomeEventType.BREAK_LESSON_APPOINTMENT;
		default:
			break;
		}
		return null;
	}

	@Override
	public String getSourceId() {
		return ((LessonAppointmentYzw)getSource()).getId()
				.toString();
	}

	@Override
	public Object getSubject() {
		return ((LessonAppointmentYzw)getSource()).getDistributer();
	}

	@Override
	public Float getValue() {
		return 1f;
	}

}
