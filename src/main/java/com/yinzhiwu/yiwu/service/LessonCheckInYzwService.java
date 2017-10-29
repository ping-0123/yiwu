package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.exception.business.LessonCheckInException;
import com.yinzhiwu.yiwu.exception.business.LessonInteractiveException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonCheckInSuccessApiView;
import com.yinzhiwu.yiwu.model.view.LessonApiView;

public interface LessonCheckInYzwService extends IBaseService<LessonCheckInYzw, Integer> {

	YiwuJson<List<LessonApiView>> findByCustomerId(int customerId);

	int findCountByCustomerId(int customerId);

	LessonCheckInSuccessApiView saveCustomerCheckIn(int distributerId, int lessonId)
			throws YiwuException, DataNotFoundException;

	PageBean<LessonApiView> findPageViewByCustomer(int customerId, Integer pageNo, Integer pageSize);

	int findCheckedInLessonsCountOfCustomer(int customerId);

	YiwuJson<PageBean<LessonApiView>> findPageOfCheckedInLessonApiViewsByContractNo(String contractNo, int pageNo,
			int pageSize);
	
	/**
	 * 如果签到人数已满,会导致已预约的用户不能签到上课
	 * @param distributer
	 * @param lesson
	 * @return
	 * @throws LessonCheckInException
	 * @throws LessonInteractiveException
	 */
	LessonCheckInYzw doCheckIn(Distributer distributer, LessonYzw lesson) throws LessonCheckInException, LessonInteractiveException;
}
