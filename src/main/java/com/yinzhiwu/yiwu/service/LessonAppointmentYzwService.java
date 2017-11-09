package com.yinzhiwu.yiwu.service;

import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.business.LessonAppointmentException;

/**
*@Author ping
*@Time  创建时间:2017年10月28日下午2:00:04
*
*/

public interface LessonAppointmentYzwService extends IBaseService<LessonAppointmentYzw,Integer>{

	@Transactional
	LessonAppointmentYzw doAppoint(Distributer distributer, LessonYzw lesson) throws LessonAppointmentException, DataNotFoundException;

	@Transactional
	LessonAppointmentYzw cancelAppoint(Distributer distributer, LessonYzw lesson) throws LessonAppointmentException;

}
