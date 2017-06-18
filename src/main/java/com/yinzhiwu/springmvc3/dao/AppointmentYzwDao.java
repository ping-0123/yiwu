package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.yzw.AppointmentYzw;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;

public interface AppointmentYzwDao extends IBaseDao<AppointmentYzw,Integer>{

	boolean isAppointed(CustomerYzw customer, LessonYzw lesson);

}
