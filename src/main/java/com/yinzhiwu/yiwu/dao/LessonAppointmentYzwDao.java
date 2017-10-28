package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonAppointmentYzw.AppointStatus;

public interface LessonAppointmentYzwDao extends IBaseDao<LessonAppointmentYzw, Integer> {

	boolean isAppointed(CustomerYzw customer, LessonYzw lesson);

	AppointStatus getAppointmentStatusByCustomerAndLesson(CustomerYzw customer, LessonYzw lesson);

	boolean isAppointable(CustomerYzw customer, LessonYzw lesson);

	LessonAppointmentYzw findAppointed(CustomerYzw customer, LessonYzw lesson, AppointStatus status);

	String getAppointedContractNo(Integer distributerId, Integer lessonId);

	List<LessonAppointmentYzw> findLastDayAppointments();

	Long findCountByLessonIdByCustomerIdByAppointStatus(Integer id, Integer id2, AppointStatus apponted);

	Long findCountByLessonIdByAppointStatus(Integer id, AppointStatus apponted);

}
