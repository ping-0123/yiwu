package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.AppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.entity.yzw.AppointmentYzw.AppointStatus;

public interface AppointmentYzwDao extends IBaseDao<AppointmentYzw, Integer> {

	boolean isAppointed(CustomerYzw customer, LessonYzw lesson);

	AppointStatus getAppointmentStatusByCustomerAndLesson(CustomerYzw customer, LessonYzw lesson);

	boolean isAppointable(CustomerYzw customer, LessonYzw lesson);

	AppointmentYzw findAppointed(CustomerYzw customer, LessonYzw lesson, AppointStatus status);

	String getAppointedContractNo(Integer distributerId, Integer lessonId);

	List<AppointmentYzw> findLastDayAppointments();

}
