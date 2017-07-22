package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.CheckInsYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.CheckInSuccessApiView;
import com.yinzhiwu.yiwu.model.view.LessonApiView;

public interface CheckInsYzwService extends IBaseService<CheckInsYzw, Integer> {


	YiwuJson<List<LessonApiView>> findByCustomerId(int customerId);

	int findCountByCustomerId(int customerId);


	CheckInSuccessApiView saveCustomerCheckIn(int distributerId, int lessonId) throws YiwuException, DataNotFoundException;


	PageBean<LessonApiView> findPageViewByCustomer(int customerId, int pageNo, int pageSize) throws Exception;




}
