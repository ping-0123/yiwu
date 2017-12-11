package com.yinzhiwu.yiwu.dao;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;

public interface LessonCheckInYzwDao extends IBaseDao<LessonCheckInYzw, Integer> {

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

	LessonCheckInYzw findByLessonIdAndCoachId(Integer lessonId, Integer coachId) throws DataNotFoundException;

	/**
	 * @param id
	 * @return
	 */
	public List<LessonCheckInYzw> findStudentCheckedinsByLessonId(Integer id);

	/**
	 * @param id
	 */
	public void setNoSettled(Integer id);

	/**
	 * @param id
	 * @return
	 */
	public LessonCheckInYzw findEffictiveCoachCheckinByLessonId(Integer id);


	/**
	 * @param id
	 * @param contractNos
	 * @return
	 */
	List<LessonCheckInYzw> findStudentCheckedinsByLessonIdContractNos(Integer id, String[] contractNos);

	/**
	 * @param id
	 * @param contractNo
	 * @return
	 */
	public LessonCheckInYzw findByLessonIdAndContractNo(Integer id, String contractNo);

	/**
	 * @return
	 */
	public List<LessonYzw> findNeedSettledLessons();

}
