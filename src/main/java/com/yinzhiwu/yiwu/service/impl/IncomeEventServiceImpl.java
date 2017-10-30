package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.IncomeEventDao;
import com.yinzhiwu.yiwu.entity.income.AppointmentEvent;
import com.yinzhiwu.yiwu.entity.income.CheckInAfterAppointEvent;
import com.yinzhiwu.yiwu.entity.income.CheckInWithoutAppointEvent;
import com.yinzhiwu.yiwu.entity.income.IncomeEvent;
import com.yinzhiwu.yiwu.entity.income.UnAppointmentEvent;
import com.yinzhiwu.yiwu.entity.income.WithdrawEvent;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw.AppointStatus;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.service.IncomeEventService;
import com.yinzhiwu.yiwu.service.IncomeRecordService;

@Service
public class IncomeEventServiceImpl extends BaseServiceImpl<IncomeEvent, Integer> implements IncomeEventService {

	@Autowired
	private IncomeRecordService incomeRecordService;

	@Autowired
	public void setBaseDao(IncomeEventDao incomeEventDao) {
		super.setBaseDao(incomeEventDao);
	}

	@Override
	public Integer save(IncomeEvent event) {
		super.save(event);
		incomeRecordService.save_records_produced_by_event(event);
		return event.getId();
	}

	@EventListener(classes={LessonAppointmentYzw.class})
	public void handleLessonAppointment(LessonAppointmentYzw appointment){
		IncomeEvent event;
		if(AppointStatus.APPONTED == appointment.getStatus())
			event = new AppointmentEvent(appointment.getDistributer(),  appointment.getLesson());
		else
			event = new UnAppointmentEvent(appointment.getDistributer(),  appointment.getLesson());
		this.save(event);
	}
	
	@EventListener(classes={LessonCheckInYzw.class})
	public void handlerLessonCheckIn(LessonCheckInYzw checkIn){
		IncomeEvent event;
		if(checkIn.getAppointed())
			event = new CheckInAfterAppointEvent(checkIn);
		else
			event = new CheckInWithoutAppointEvent(checkIn);
		
		this.save(event);
	}
	
	@EventListener(classes={WithdrawEvent.class})
	public void handleWithdrawBrokerage(WithdrawEvent event){
		this.save(event);
	}
	
}
