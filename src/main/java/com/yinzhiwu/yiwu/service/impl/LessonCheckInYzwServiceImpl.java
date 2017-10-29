package com.yinzhiwu.yiwu.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CheckInEventDao;
import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.LessonAppointmentYzwDao;
import com.yinzhiwu.yiwu.dao.LessonCheckInYzwDao;
import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.entity.income.CheckInAfterAppointEvent;
import com.yinzhiwu.yiwu.entity.income.CheckInWithoutAppointEvent;
import com.yinzhiwu.yiwu.entity.income.IncomeEvent;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.exception.business.LessonCheckInException;
import com.yinzhiwu.yiwu.exception.business.LessonInteractiveException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.model.view.LessonCheckInSuccessApiView;
import com.yinzhiwu.yiwu.service.IncomeEventService;
import com.yinzhiwu.yiwu.service.LessonCheckInYzwService;
import com.yinzhiwu.yiwu.service.LessonInteractiveService;

@Service
public class LessonCheckInYzwServiceImpl extends BaseServiceImpl<LessonCheckInYzw, Integer> implements LessonCheckInYzwService {

	@Autowired
	private LessonCheckInYzwDao lessonCheckInDao;
	@Autowired
	private CheckInEventDao checkInEventDao;
	@Autowired
	private OrderYzwDao orderDao;
	@Autowired
	private LessonAppointmentYzwDao appointmentDao;
	@Autowired
	private IncomeEventService incomeEventService;
	@Autowired
	private DistributerDao distibuterDao;
	@Autowired
	private LessonYzwDao lessonDao;
	@Autowired
	private CustomerYzwDao customerDao;
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
	public LessonCheckInSuccessApiView saveCustomerCheckIn(int distributerId, int lessonId)
			throws YiwuException {
		
		boolean isAppointed = false;
		Distributer distributer;
		try {
			distributer = distibuterDao.get(distributerId);
		} catch (DataNotFoundException e1) {
			throw new RuntimeException(e1);
		}
		if (distributer == null)
			throw new YiwuException(distributerId + "用户不存在.");
		LessonYzw lesson;
		try {
			lesson = lessonDao.get(lessonId);
		} catch (DataNotFoundException e1) {
			throw new RuntimeException(e1);
		}
		if (lesson == null)
			throw new YiwuException(lessonId + "预约的课程不存在");
		CustomerYzw customer = distributer.getCustomer();
		if (customer == null)
			throw new YiwuException(distributer.getId() + "客户不存在");
		if (CourseType.CLOSED== lesson.getCourseType())
			throw new YiwuException("封闭式课程无须刷卡");

		if (lessonCheckInDao.isCheckedIn(customer.getMemberCard(), lesson.getId()))
			throw new YiwuException("已刷卡， 无须重复刷卡");
		//上课时间是否已已过
		Calendar end = Calendar.getInstance();
		end.setTime(lesson.getStartDateTime());
		end.add(Calendar.MINUTE, lesson.getLessonMinutes());
		if(Calendar.getInstance().after(end))
			throw new YiwuException("课程已结束");
		//获取已经预约的会籍合约
		String contractNo = null;
		try{
			contractNo = appointmentDao.getAppointedContractNo(distributer.getId(), lesson.getId());
		}catch (Exception e) {
			String message = e.getMessage();
			message.replace("预约", "签到");
			throw new YiwuException(message, e);
		}
		
		if(contractNo != null)
			isAppointed = true;
		else{
			//判断是否能刷卡
			try {
				Contract contract = orderDao.findCheckableContractOfCustomerAndLesson(customer, lesson);
				contractNo = contract.getContractNo();
			} catch (DataNotFoundException e) {
				String message = e.getMessage();
				logger.error(message,e);
				message.replace("预约", "签到");
				throw new YiwuException(message);
			}
		}
			
		
		// 刷卡
		LessonCheckInYzw checkIn = new LessonCheckInYzw(distributer, lesson, contractNo);
		lessonCheckInDao.save(checkIn);
		/**
		 * 判断是否预约, 并保存刷卡事件
		 */
		IncomeEvent event = null;
		if (isAppointed) {
			event = new CheckInAfterAppointEvent(distributer, checkIn);
		} else{
			event = new CheckInWithoutAppointEvent(distributer,checkIn);
			orderDao.updateContractWithHoldTimes(contractNo, 1);
		}
		incomeEventService.save(event);
		
		return checkInEventDao.findCheckInSuccessApiViewById(event.getId());
	}

	@Override
	public YiwuJson<PageBean<LessonApiView>> findPageOfCheckedInLessonApiViewsByContractNo(String contractNo,
			int pageNo, int pageSize) {
		PageBean<LessonApiView> page = lessonCheckInDao.findPageOfLessonApiViewsByContractNo(contractNo, pageNo, pageSize);
		return YiwuJson.createBySuccess(page);
	}

	@Override
	public LessonCheckInYzw doCheckIn(Distributer distributer, LessonYzw lesson) throws LessonCheckInException, LessonInteractiveException {
		
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
		Calendar checkInEndCalendar = Calendar.getInstance();
		checkInEndCalendar.setTime(lesson.getEndTime());
		calendar.set(Calendar.HOUR_OF_DAY, checkInEndCalendar.get(Calendar.HOUR_OF_DAY));
		Date checkInEnd = calendar.getTime();
		if(current.after(checkInEnd))
			throw new LessonCheckInException("上课已结束, 请在上课前1小时内刷卡");
		//判断上课人数是否已满
		if(lesson.getCheckedInStudentCount()> lesson.getClassRoom().getMaxStudentCount())
			throw new LessonCheckInException("上课人数已满");
		//判断是否已刷卡
		LessonInteractive interactive = lessonInteractiveService.ensureInteractive(lesson, distributer);
		if(interactive.getCheckedIn())
			throw new LessonCheckInException("已刷卡，无须重复刷卡");
		
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
