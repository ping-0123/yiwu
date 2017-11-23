package com.yinzhiwu.yiwu.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.dao.LessonCheckInYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.entity.yzw.ClassRoomYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.business.LessonCheckInException;
import com.yinzhiwu.yiwu.exception.business.LessonInteractiveException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.service.LessonCheckinService;
import com.yinzhiwu.yiwu.service.LessonInteractiveService;

@Service
public class LessonCheckinServiceImpl extends BaseServiceImpl<LessonCheckInYzw, Integer> implements LessonCheckinService {

	@Autowired
	private LessonCheckInYzwDao lessonCheckInDao;
	@Autowired private CustomerYzwDao customerDao;
	@Autowired private LessonInteractiveService lessonInteractiveService;
	@Autowired private ApplicationContext applicationContext;
	
	@Autowired
	public void setBaseDao(LessonCheckInYzwDao checkInsYzwDao) {
		super.setBaseDao(checkInsYzwDao);
	}

	@Override
	public int findCountByCustomerId(int customerId) {
		return lessonCheckInDao.findCountByCustomerId(customerId);
	}

	@Override
	public int findCheckedInLessonsCountOfCustomer(int customerId) {
		String memberCard;
		try {
			memberCard = customerDao.get(customerId).getMemberCard();
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}
		return lessonCheckInDao.findCheckedInLessonsCountByMemeberCard(memberCard);
	}
	
	@Override
	public YiwuJson<List<LessonApiView>> findByCustomerId(int customerId) {
		String memberCard;
		try {
			memberCard = customerDao.get(customerId).getMemberCard();
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
		List<LessonApiView> lessonApiViews = lessonCheckInDao.findLessonApiViewsByMemeberCard(memberCard);
		return YiwuJson.createBySuccess(lessonApiViews);
	}

	@Override
	public PageBean<LessonApiView> findPageViewByCustomer(int customerId, Integer pageNo, Integer pageSize) {
		String memberCard;
		try {
			memberCard = customerDao.get(customerId).getMemberCard();
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return null;
		}
		return lessonCheckInDao.findPageCheckedInLessonApiViewsByMemberCard(memberCard, pageNo, pageSize);

	}


	@Override
	public YiwuJson<PageBean<LessonApiView>> findPageOfCheckedInLessonApiViewsByContractNo(String contractNo,
			int pageNo, int pageSize) {
		PageBean<LessonApiView> page = lessonCheckInDao.findPageOfLessonApiViewsByContractNo(contractNo, pageNo, pageSize);
		return YiwuJson.createBySuccess(page);
	}

	@Override
	public LessonCheckInYzw doCheckIn(Distributer distributer, LessonYzw lesson) throws LessonCheckInException, LessonInteractiveException {
		if(null == distributer)
			throw new LessonCheckInException("签到人不能为空");
		if(null == lesson)
			throw new LessonCheckInException("签到的课程不能为空");
		
		//封闭式课不需要刷卡
		if(CourseType.CLOSED == lesson.getCourseType())
			throw new LessonCheckInException("封闭式课程无须刷卡");
		//刷卡时间判定
		Calendar calendar = Calendar.getInstance();
		Date current = calendar.getTime();
		calendar.setTime(lesson.getStartDateTime());
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		Date checkInStart = calendar.getTime();
		if(current.before(checkInStart))
			throw new LessonCheckInException("离上课时间还远,请在上课前1小时内刷卡");
		
/*   TODO 刷卡结束时间暂不做限制
 * 		Calendar checkInEndCalendar = Calendar.getInstance();
		checkInEndCalendar.setTime(lesson.getEndTime());
		calendar.set(Calendar.HOUR_OF_DAY, checkInEndCalendar.get(Calendar.HOUR_OF_DAY));
		Date checkInEnd = calendar.getTime();
		if(current.after(checkInEnd))
			throw new LessonCheckInException("上课已结束, 请在上课前1小时内刷卡");*/
		
		//判断是否已刷卡
		LessonInteractive interactive = lessonInteractiveService.ensureInteractive(lesson, distributer);
		if(interactive.getCheckedIn())
			throw new LessonCheckInException("已刷卡，无须重复刷卡");
		
		//判断上课人数是否已满
		int checkedCount = lesson.getCheckedInStudentCount()==null?0:lesson.getCheckedInStudentCount();
		int maxCount = null== lesson.getClassRoom()? ClassRoomYzw.DEFAULT_MAX_STUDENT_COUNT:
			(null == lesson.getClassRoom().getMaxStudentCount()? ClassRoomYzw.DEFAULT_MAX_STUDENT_COUNT: lesson.getClassRoom().getMaxStudentCount());
		if(checkedCount>= maxCount)
			throw new LessonCheckInException("上课人数已满");

		
		//保存签到
		LessonCheckInYzw checkIn = new LessonCheckInYzw();
		checkIn.setDistributer(distributer);
		checkIn.setMemberCard(distributer.getMemberCard());
		checkIn.setLesson(lesson);
		checkIn.setContractNo(interactive.getContracNo());
		checkIn.setAppointed(interactive.getAppointed());
		lessonCheckInDao.save(checkIn);
		
		//推送签到事件
		applicationContext.publishEvent(checkIn);
		
		//返回
		return checkIn;
		
	}




}
