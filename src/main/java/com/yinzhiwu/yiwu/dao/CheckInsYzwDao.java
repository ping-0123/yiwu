package com.yinzhiwu.yiwu.dao;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.CheckInsYzw;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;

public interface CheckInsYzwDao extends IBaseDao<CheckInsYzw, Integer> {

	int findCountByCustomerId(int customerId);

	boolean isCheckedIn(CustomerYzw customer, LessonYzw lesson);

	PageBean<LessonApiView> findPageByContractNos(List<String> contractNos, int pageNo, int pageSize);

	Date findCheckInTimeByProperties(Integer lessonId, Integer actualTeacherId);

	boolean isCheckedIn(String memberCard, Integer lessonId);

	boolean isCheckedIn(Integer distributerId, Integer lessonId);

	List<LessonApiView> findLessonApiViewsByMemeberCard(String memberCard);

	/**
	 * 分页查询会员已上课时
	 * @param memberCard
	 * @return
	 */
	PageBean<LessonApiView> findPageCheckedInLessonApiViewsByMemberCard(String memberCard, Integer pageNo, Integer pageSize);

	int findCheckedInLessonsCountByMemeberCard(String memberCard);

	StoreApiView findStoreApiViewOfLastCheckedOpenLesson(String memberCard);

	PageBean<LessonApiView> findPageOfLessonApiViewsByContractNo(String contractNo, int pageNo, int pageSize);

	List<CheckInsYzw> findByLessonId(Integer id);

	/**
	 * 查询该合约所有已上课正常结算的课程课时汇总
	 * @param contractNo
	 * @return
	 */
	Float findSumHoursOfCheckedLessonsByContractNo(String contractNo);

	List<LessonYzw> findCheckedInlessonsByDate(Date start, Date end);

}
