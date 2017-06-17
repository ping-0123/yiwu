package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.yzw.CheckInsYzw;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CheckInSuccessApiView;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;

public interface CheckInsYzwService extends IBaseService<CheckInsYzw, Integer> {


	YiwuJson<List<LessonApiView>> findByCustomerId(int customerId);

	int findCountByCustomerId(int customerId);

	CheckInSuccessApiView saveCustomerCheckIn(CustomerYzw customer, LessonYzw lesson) throws Exception;




}
