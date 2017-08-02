package com.yinzhiwu.yiwu.dao;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.CheckInsYzw;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;

public interface CheckInsYzwDao extends IBaseDao<CheckInsYzw, Integer> {

	int findCountByContractNos(List<String> contractNos);

	List<LessonYzw> findByContractNos(List<String> contractNos);

	int findCountByCustomerId(int customerId);

	boolean isCheckedIn(CustomerYzw customer, LessonYzw lesson);

	PageBean<LessonApiView> findPageByContractNos(List<String> contractNos, int pageNo, int pageSize);

	Date findCheckInTimeByProperties(Integer lessonId, Integer actualTeacherId);

}
